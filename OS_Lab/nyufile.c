#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <ctype.h>

#pragma pack(push, 1)
typedef struct BootEntry
{
    unsigned char BS_jmpBoot[3];    // Assembly instruction to jump to boot code
    unsigned char BS_OEMName[8];    // OEM Name in ASCII
    unsigned short BPB_BytsPerSec;  // Bytes per sector. Allowed values include 512, 1024, 2048, and 4096
    unsigned char BPB_SecPerClus;   // Sectors per cluster (data unit). Allowed values are powers of 2, but the cluster size must be 32KB or smaller
    unsigned short BPB_RsvdSeccount;  // Size in sectors of the reserved area
    unsigned char BPB_NumFATs;      // Number of FATs
    unsigned short BPB_RootEntcount;  // Maximum number of files in the root directory for FAT12 and FAT16. This is 0 for FAT32
    unsigned short BPB_TotSec16;    // 16-bit value of number of sectors in file system
    unsigned char BPB_Media;        // Media type
    unsigned short BPB_FATSz16;     // 16-bit size in sectors of each FAT for FAT12 and FAT16. For FAT32, this field is 0
    unsigned short BPB_SecPerTrk;   // Sectors per track of storage device
    unsigned short BPB_NumHeads;    // Number of heads in storage device
    unsigned int BPB_HiddSec;       // Number of sectors before the start of partition
    unsigned int BPB_TotSec32;      // 32-bit value of number of sectors in file system. Either this value or the 16-bit value above must be 0
    unsigned int BPB_FATSz32;       // 32-bit size in sectors of one FAT
    unsigned short BPB_ExtFlags;    // A flag for FAT
    unsigned short BPB_FSVer;       // The major and minor version number
    unsigned int BPB_RootClus;      // Cluster where the root directory can be found
    unsigned short BPB_FSInfo;      // Sector where FSINFO structure can be found
    unsigned short BPB_BkBootSec;   // Sector where backup copy of boot sector is located
    unsigned char BPB_Reserved[12]; // Reserved
    unsigned char BS_DrvNum;        // BIOS INT13h drive number
    unsigned char BS_Reserved1;     // Not used
    unsigned char BS_BootSig;       // Extended boot signature to identify if the next three values are valid
    unsigned int BS_VolID;          // Volume serial number
    unsigned char BS_VolLab[11];    // Volume label in ASCII. User defines when creating the file system
    unsigned char BS_FilSysType[8]; // File system type label in ASCII
} BootEntry;
#pragma pack(pop)

#pragma pack(push, 1)
typedef struct DirEntry
{
    unsigned char DIR_Name[11];     // File name
    unsigned char DIR_Attr;         // File attributes
    unsigned char DIR_NTRes;        // Reserved
    unsigned char DIR_CrtTimeTenth; // Created time (tenths of second)
    unsigned short DIR_CrtTime;     // Created time (hours, minutes, seconds)
    unsigned short DIR_CrtDate;     // Created day
    unsigned short DIR_LstAccDate;  // Accessed day
    unsigned short DIR_FstClusHI;   // High 2 bytes of the first cluster address
    unsigned short DIR_WrtTime;     // Written time (hours, minutes, seconds
    unsigned short DIR_WrtDate;     // Written day
    unsigned short DIR_FstClusLO;   // Low 2 bytes of the first cluster address
    unsigned int DIR_FileSize;      // File size in bytes. (0 for directories)
} DirEntry;
#pragma pack(pop)

void usage()
{
    printf("Usage: ./nyufile disk <options>\n");
    printf("  -i                     Print the file system information.\n");
    printf("  -l                     List the root directory.\n");
    printf("  -r filename [-s sha1]  Recover a contiguous file.\n");
    printf("  -R filename -s sha1    Recover a possibly non-contiguous file.\n");
}

void format_name(const char *input, char *output)
{
    int i, j;

    for (i = 0, j = 0; i < 8 && input[j] != '.' && input[j] != '\0'; i++, j++)
    {
        output[i] = toupper(input[j]);
    }

    while (i < 8)
    {
        output[i++] = ' ';
    }

    if (input[j] == '.')
    {
        j++;
    }

    for (; i < 11 && input[j] != '\0'; i++, j++)
    {
        output[i] = toupper(input[j]);
    }

    while (i < 11)
    {
        output[i++] = ' ';
    }
}

void print_fsinfo(char *disk_image)
{
    int fd;
    BootEntry boot_entry;

    fd = open(disk_image, O_RDONLY);
    if (fd < 0)
    {
        perror("Error opening disk image");
        exit(1);
    }

    if (read(fd, &boot_entry, sizeof(boot_entry)) != sizeof(boot_entry))
    {
        perror("Error reading boot entry");
        close(fd);
        exit(1);
    }

    printf("Number of FATs = %u\n", boot_entry.BPB_NumFATs);
    printf("Number of bytes per sector = %hu\n", boot_entry.BPB_BytsPerSec);
    printf("Number of sectors per cluster = %u\n", boot_entry.BPB_SecPerClus);
    printf("Number of reserved sectors = %hu\n", boot_entry.BPB_RsvdSeccount);

    close(fd);
}

void list_rd(const char *disk_image)
{
    int fd;
    struct stat sb;
    void *mapped_data;
    BootEntry *boot_entry;
    DirEntry *dir_entry;
    unsigned int root_dir_offset, root_dir_size, entry_count = 0;

    fd = open(disk_image, O_RDONLY);

    if (fd < 0)
    {
        exit(1);
    }

    if (fstat(fd, &sb) < 0)
    {
        close(fd);
        exit(1);
    }

    mapped_data = mmap(NULL, sb.st_size, PROT_READ, MAP_SHARED, fd, 0);
    if (mapped_data == MAP_FAILED)
    {
        close(fd);
        exit(1);
    }

    boot_entry = (BootEntry *)mapped_data;
    root_dir_offset = (boot_entry->BPB_RsvdSeccount + boot_entry->BPB_NumFATs * boot_entry->BPB_FATSz32) * boot_entry->BPB_BytsPerSec;
    root_dir_size = boot_entry->BPB_SecPerClus * boot_entry->BPB_BytsPerSec;
    dir_entry = (DirEntry *)((char *)mapped_data + root_dir_offset);

    for (unsigned int i = 0; i < root_dir_size / sizeof(DirEntry); i++)
    {
        if (dir_entry[i].DIR_Name[0] == 0x00)
        {
            break;
        }

        if (dir_entry[i].DIR_Name[0] == 0xE5 || dir_entry[i].DIR_Attr == 0x0F)
        {
            continue;
        }

        char filename[13];
        strncpy(filename, (const char *)dir_entry[i].DIR_Name, 8);
        int filename_len = 8;
        while (filename_len > 0 && filename[filename_len - 1] == ' ')
        {
            filename_len--;
        }
        if (!(dir_entry[i].DIR_Attr & 0x10) && !(dir_entry[i].DIR_Name[8] == ' ' && dir_entry[i].DIR_Name[9] == ' ' && dir_entry[i].DIR_Name[10] == ' '))
        {
            filename[filename_len++] = '.';
            strncpy(filename + filename_len, (const char *)(dir_entry[i].DIR_Name + 8), 3);
            filename_len += 3;
        }
        filename[filename_len] = '\0';
        if (dir_entry[i].DIR_Attr & 0x10)
        {
            printf("%s/ (starting cluster = %u)\n", filename, dir_entry[i].DIR_FstClusLO);
        }
        else
        {
            if (dir_entry[i].DIR_FileSize == 0)
            {
                printf("%s (size = 0)\n", filename);
            }
            else
            {
                printf("%s (size = %u, starting cluster = %u)\n", filename, dir_entry[i].DIR_FileSize, dir_entry[i].DIR_FstClusLO);
            }
        }

        entry_count++;
    }

    printf("Total number of entries = %u\n", entry_count);

    if (munmap(mapped_data, sb.st_size) < 0)
    {
        close(fd);
        exit(1);
    }
    close(fd);
}

void recover_small_file(const char *disk_image, const char *filename)
{
    int fd = open(disk_image, O_RDWR);
    if (fd == -1)
    {
        exit(1);
    }

    struct stat sb;
    if (fstat(fd, &sb) == -1)
    {
        close(fd);
        exit(1);
    }

    unsigned char *mapped_data = mmap(NULL, sb.st_size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (mapped_data == MAP_FAILED)
    {
        close(fd);
        exit(1);
    }

    BootEntry *boot_entry = (BootEntry *)mapped_data;
    unsigned int root_dir_sectors = ((boot_entry->BPB_RootEntcount * 32) + (boot_entry->BPB_BytsPerSec - 1)) / boot_entry->BPB_BytsPerSec;
    unsigned int first_data_sector = boot_entry->BPB_RsvdSeccount + (boot_entry->BPB_NumFATs * boot_entry->BPB_FATSz32) + root_dir_sectors;
    unsigned int root_dir_addr = (boot_entry->BPB_RootClus - 2) * boot_entry->BPB_SecPerClus + first_data_sector;
    root_dir_addr *= boot_entry->BPB_BytsPerSec;

    DirEntry *dir_entry = (DirEntry *)(mapped_data + root_dir_addr);
    unsigned int root_dir_size = boot_entry->BPB_SecPerClus * boot_entry->BPB_BytsPerSec;

    int found = 0;
    for (unsigned int i = 0; i < root_dir_size / sizeof(DirEntry); i++)
    {
        if (dir_entry[i].DIR_Name[0] == 0xE5)
        {
            char entry_filename[13];
            strncpy(entry_filename, (const char *)dir_entry[i].DIR_Name, 8);
            int filename_len = 8;
            while (filename_len > 0 && entry_filename[filename_len - 1] == ' ') {
                filename_len--;
            }
            if (!(dir_entry[i].DIR_Attr & 0x10) && !(dir_entry[i].DIR_Name[8] == ' ' && dir_entry[i].DIR_Name[9] == ' ' && dir_entry[i].DIR_Name[10] == ' ')) {
                entry_filename[filename_len++] = '.';
                strncpy(entry_filename + filename_len, (const char *)(dir_entry[i].DIR_Name + 8), 3);
                filename_len += 3;
            }
            entry_filename[filename_len] = '\0';

            if (strcmp(entry_filename + 1, filename + 1) == 0)
            {
                found = 1;

                dir_entry[i].DIR_Name[0] = filename[0];
                msync(mapped_data, sb.st_size, MS_SYNC);

                printf("%s: successfully recovered\n", filename);
                break;
            }
        }
    }

    if (!found)
    {
        printf("%s: file not found\n", filename);
    }
    if (munmap(mapped_data, sb.st_size) == -1)
    {
        close(fd);
        exit(1);
    }
    close(fd);
}

int main(int argc, char *argv[])
{
    int opt;
    int iFlag = 0;
    int lFlag = 0;
    int r_flag = 0;
    int R_flag = 0;
    // int sFlag = 0;
    char *filename = NULL;
    char *sha1 = NULL;

    while ((opt = getopt(argc, argv, "ilr:R:s:")) != -1)
    {
        switch (opt)
        {
        case 'i':
            iFlag = 1;
            break;
        case 'l':
            lFlag = 1;
            break;
        case 'r':
            r_flag = 1;
            filename = optarg;
            break;
        case 'R':
            R_flag = 1;
            filename = optarg;
            break;
        case 's':
            // sFlag = 1;
            sha1 = optarg;
            break;
        case '?':
            usage();
            exit(1);
        }
    }

    if (optind != argc - 1)
    {
        usage();
        exit(1);
    }

    char *disk_image = argv[optind];

    if (iFlag + lFlag + r_flag + R_flag != 1)
    {
        usage();
        exit(1);
    }

    if (r_flag && !filename)
    {
        usage();
        exit(1);
    }

    if (R_flag && (!filename || !sha1))
    {
        usage();
        exit(1);
    }

    if (iFlag)
    {
        print_fsinfo(disk_image);
    }
    else if (lFlag)
    {
        list_rd(disk_image);
    }
    else if (r_flag)
    {
        recover_small_file(disk_image, filename);
    }
    else if (R_flag)
    {
        recover_small_file(disk_image, filename);
    }

    return 0;
}