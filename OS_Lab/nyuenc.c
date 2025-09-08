#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <getopt.h>

#define CHUNK_SIZE 4096

typedef struct task_struct
{
    unsigned char *input_chunk;
    int input_size;
    unsigned char *output_chunk;
    int *output_size;
} task_t;

task_t *task_queue;
pthread_mutex_t task_queue_mutex;
pthread_cond_t task_queue_cond;
int num_tasks;
int task_queue_front, task_queue_rear;

void enqueue_task(unsigned char *input_chunk, int input_size, unsigned char *output_chunk, int *output_size)
{
    pthread_mutex_lock(&task_queue_mutex);
    task_queue_rear = (task_queue_rear + 1) % num_tasks;
    task_queue[task_queue_rear].input_chunk = input_chunk;
    task_queue[task_queue_rear].input_size = input_size;
    task_queue[task_queue_rear].output_chunk = output_chunk;
    task_queue[task_queue_rear].output_size = output_size;
    pthread_cond_signal(&task_queue_cond);
    pthread_mutex_unlock(&task_queue_mutex);
}

task_t dequeue_task()
{
    pthread_mutex_lock(&task_queue_mutex);
    while (task_queue_front == task_queue_rear)
    {
        pthread_cond_wait(&task_queue_cond, &task_queue_mutex);
    }
    task_queue_front = (task_queue_front + 1) % num_tasks;
    task_t task = task_queue[task_queue_front];
    pthread_mutex_unlock(&task_queue_mutex);
    return task;
}

void *worker_thread_func()
{
    //int id = *(int *)arg;
    while (1)
    {
        task_t task = dequeue_task();
        if (task.input_chunk == NULL)
        {
            break;
        }
        int i = 0, j = 0, count = 0;
        while (i < task.input_size)
        {
            task.output_chunk[j++] = task.input_chunk[i];
            count = 1;
            while (i + count < task.input_size && task.input_chunk[i] == task.input_chunk[i + count])
            {
                count++;
            }
            task.output_chunk[j++] = count;
            i += count;
        }
        *task.output_size = j;
        free(task.input_chunk);
        free(task.output_chunk);
    }
    return NULL;
}

void rle_encode(unsigned char *input_buffer, int input_size, unsigned char *output_buffer, int *output_size, int num_threads)
{
    int num_chunks = (input_size + CHUNK_SIZE - 1) / CHUNK_SIZE;
    pthread_t *worker_threads = (pthread_t *)malloc(num_threads * sizeof(pthread_t));
    int *thread_ids = (int *)malloc(num_threads * sizeof(int));
    task_queue = (task_t *)malloc(num_chunks * sizeof(task_t));
    pthread_mutex_init(&task_queue_mutex, NULL);
    pthread_cond_init(&task_queue_cond, NULL);
    num_tasks = num_chunks;
    task_queue_front = task_queue_rear = 0;
    // Create worker threads
    for (int i = 0; i < num_threads; i++)
    {
        thread_ids[i] = i;
        pthread_create(&worker_threads[i], NULL, worker_thread_func, &thread_ids[i]);
    }

    // Enqueue tasks
    for (int i = 0; i < num_chunks; i++)
    {
        unsigned char *input_chunk = (unsigned char *)malloc(CHUNK_SIZE);
        int input_chunk_size = (i == num_chunks - 1) ? (input_size % CHUNK_SIZE) : CHUNK_SIZE;
        memcpy(input_chunk, input_buffer + i * CHUNK_SIZE, input_chunk_size);

        unsigned char *output_chunk = (unsigned char *)malloc(2 * input_chunk_size); // output size is at most twice input size
        int *output_chunk_size = (int *)malloc(sizeof(int));

        enqueue_task(input_chunk, input_chunk_size, output_chunk, output_chunk_size);
    }

    // Signal termination of worker threads
    for (int i = 0; i < num_threads; i++)
    {
        enqueue_task(NULL, 0, NULL, NULL);
    }

    // Wait for worker threads to terminate
    for (int i = 0; i < num_threads; i++)
    {
        pthread_join(worker_threads[i], NULL);
    }

    // Concatenate output chunks
    *output_size = 0;
    for (int i = 0; i < num_chunks; i++)
    {
        *output_size += *(task_queue[i].output_size);
    }
    int j = 0;
    for (int i = 0; i < num_chunks; i++)
    {
        memcpy(output_buffer + j, task_queue[i].output_chunk, *(task_queue[i].output_size));
        j += *(task_queue[i].output_size);
    }

    // Free resources
    free(worker_threads);
    free(thread_ids);
    for (int i = 0; i < num_chunks; i++)
    {
        free(task_queue[i].output_chunk);
        free(task_queue[i].output_size);
    }
    free(task_queue);
    pthread_mutex_destroy(&task_queue_mutex);
    pthread_cond_destroy(&task_queue_cond);
}

int main(int argc, char **argv)
{
    int num_threads = 1;
    int opt;
    while ((opt = getopt(argc, argv, "j:")) != -1)
    {
        switch (opt)
        {
        case 'j':
            num_threads = atoi(optarg);
            break;
        default:
            fprintf(stderr, "Usage: %s [-j num_threads] input_file\n", argv[0]);
            exit(EXIT_FAILURE);
        }
    }
    if (optind >= argc)
    {
        fprintf(stderr, "Usage: %s [-j num_threads] input_file\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    const char *input_filename = argv[optind];

    FILE *input_file = fopen(input_filename, "rb");
    if (input_file == NULL)
    {
        fprintf(stderr, "Error: could not open input file '%s'\n", input_filename);
        exit(EXIT_FAILURE);
    }

    // Determine input file size
    fseek(input_file, 0, SEEK_END);
    int input_size = ftell(input_file);
    fseek(input_file, 0, SEEK_SET);

    // Allocate input buffer
    unsigned char *input_buffer = (unsigned char *)malloc(input_size);
    if (input_buffer == NULL)
    {
        fprintf(stderr, "Error: could not allocate input buffer\n");
        fclose(input_file);
        exit(EXIT_FAILURE);
    }

    // Read input file into buffer
    int bytes_read = fread(input_buffer, 1, input_size, input_file);
    if (bytes_read != input_size)
    {
        fprintf(stderr, "Error: could not read input file\n");
        fclose(input_file);
        free(input_buffer);
        exit(EXIT_FAILURE);
    }

    fclose(input_file);

    // Allocate output buffer
    unsigned char *output_buffer = (unsigned char *)malloc(input_size * 2);
    if (output_buffer == NULL)
    {
        fprintf(stderr, "Error: could not allocate output buffer\n");
        free(input_buffer);
        exit(EXIT_FAILURE);
    }

    int output_size = 0;

    rle_encode(input_buffer, input_size, output_buffer, &output_size, num_threads);

    fwrite(output_buffer, 1, output_size, stdout);

    free(input_buffer);
    free(output_buffer);

    return 0;
}
