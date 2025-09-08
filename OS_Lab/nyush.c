#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include <libgen.h>
#include <signal.h>

#define UNUSED(x) (void)(x)

void sigHandler(int sig) {
    //handle signals
    UNUSED(sig);
}

int parseCommand(char **args, char *line){
    char *token = strtok(line, " ");
        int i = 0;
        while (token != NULL) {
            args[i] = token;
            token = strtok(NULL, " ");
            i++;
        }
    args[i] = NULL;
    return i;
}

int outputRedirection(char **args){
    int fd = -1;
    for (int i = 0; args[i] != NULL; i++) {
        if (strcmp(args[i], ">") == 0 || strcmp(args[i], ">>") == 0) {
            //output redirection detected
            if (args[i+1] == NULL) {
                fprintf(stderr, "Error: invalid command\n");
                args[i-1] = NULL;
                args[i] = NULL;
                continue;
            }

            //open the output file for writing, creating it if necessary
            if (strcmp(args[i], ">")) {
                fd = open(args[i+1], O_WRONLY | O_CREAT | O_TRUNC, 0644);
            } else {
                fd = open(args[i+1], O_WRONLY | O_CREAT | O_APPEND, 0644);
            }

            if (fd == -1) {
                fprintf(stderr, "Error: invalid file\n");
                args[i-1] = NULL;
                args[i] = NULL;
                args[i+1] = NULL;
                continue;
            }

            //remove from the argument list
            args[i] = NULL;
            args[i+1] = NULL;
            break;
        }
    }
    return fd;
}

int inputRedirection(char **args){
    int fd = -1;
    for (int i = 0; args[i] != NULL; i++) {
        if (strcmp(args[i], "<") == 0) {
            //input redirection detected
            if (args[i+1] == NULL) {
                fprintf(stderr, "Error: invalid command\n");
                args[i-1] = NULL;
                args[i] = NULL;
                continue;
            }

            // oen the input file for reading
            fd = open(args[i+1], O_RDONLY);
            if (fd == -1) {
                fprintf(stderr, "Error: invalid file\n");
                args[i-1] = NULL;
                args[i] = NULL;
                args[i+1] = NULL;
                continue;
            }

            //remove from the argument list
            args[i] = NULL;
            args[i+1] = NULL;
            break;
        }
    }
    return fd;
}

/*
void builtInCommands(char **args){
    
}*/

int main() {
    char cwd[1024];
    ssize_t read;
    char *line = NULL;
    size_t len = 0;
    int status;

    while (1) {
        //handle signals
        signal(SIGINT, sigHandler);
        signal(SIGQUIT, sigHandler);
        signal(SIGTSTP, sigHandler);

        if (getcwd(cwd, sizeof(cwd)) != NULL) {
            char *cwdBase = basename(cwd);
            printf("[nyush %s]$ ", cwdBase);
            fflush(stdout);
        } else {
            fprintf(stderr, "Error: invalid program\n");
            exit(1);
        }

        //get input
        read = getline(&line, &len, stdin);
        if (read == -1) {
            break;
        }
        line[read-1] = '\0';

        //parse commad
        char *args[1000];
        int argNum = parseCommand(args, line);

        if (argNum == 0){
            continue;
        }

        //cd and exit
        if (strcmp(args[0], "cd") == 0) {
            if (args[1] == NULL) {
                fprintf(stderr, "Error: invalid command\n");
            } else if (args[2] != NULL) {
                fprintf(stderr, "Error: invalid command\n");
            } else {
                if (chdir(args[1]) != 0) {
                    fprintf(stderr, "Error: invalid directory\n");
                }
            }
            continue;

        } else if (strcmp(args[0], "exit") == 0) {
            if (args[1] != NULL) {
                fprintf(stderr, "Error: invalid command\n");
                continue;
            } else {
                break;
            }
        }

        //input and output redirection
        //int fdOut = outputRedirection(args);
        //int fdIn = inputRedirection(args);

        //Implement fork
        pid_t pid = fork();
        if (pid < 0) {
            fprintf(stderr, "Error: invalid program\n");
            exit(1);
        } else if (pid == 0) {
            //child process

            //restore signals
            signal(SIGINT, SIG_DFL);
            signal(SIGQUIT, SIG_DFL);
            signal(SIGTSTP, SIG_DFL);

            //check for I/O
            int fdOut = outputRedirection(args);
            int fdIn = inputRedirection(args);

            if (fdOut != -1){ //output redir
                if(dup2(fdOut, STDOUT_FILENO) == -1){
                    fprintf(stderr, "Error: invalid program\n");
                    exit(1);
                }
                close(fdOut);
            }
            if (fdIn != -1){ //input redir
                if(dup2(fdIn, STDIN_FILENO) == -1){
                    fprintf(stderr, "Error: invalid program\n");
                    exit(1);
                }
                close(fdIn);
            }

            if (access(args[0], F_OK) != -1) {
                execvp(args[0], args);
            }

            char* path = getenv("PATH");
            char* p = strtok(path, ":");
            while (p != NULL){
                char program[1000];
                strcpy(program, p);
                strcat(program, "/");
                strcat(program, args[0]);
                if (access(program, F_OK) != -1) {
                    execvp(program, args);
                }
                p = strtok(NULL, ":");
            }

            fprintf(stderr, "Error: invalid program\n");
            exit(1);
        } else {
            //parent process
            waitpid(pid, &status, 0);
        }
    }

    free(line);
    exit(0);
}