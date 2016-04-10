#include <string.h>
#include <stdio.h>
#include <pthread.h>

#define NUM_THREADS 5

const char *delim = " ,.;?!";

char str[5][128] = {
	"The people who can destroy a thing, they control it.",
	"Survival is the ability to swim in strange water.",
	"Hope clouds observation.",
	"A man's flesh is his own; the water belongs to the tribe.",
	"You can't buy security."
};

void print_words(char *text)
{
    char *p = text;
    p = strtok(text, delim);

    while (p != NULL)
    {
        printf("%s\n", p);
        p = strtok(NULL, delim);
    }
}

void *thread_routine(void *arg)
{
	int thread_idx = (int) arg;
    print_words(str[thread_idx]);
    return NULL;
}

int main(void)
{
	int i;
	pthread_t tids[NUM_THREADS];

	for (i = 0; i < NUM_THREADS; i++)
		pthread_create(&tids[i], NULL, thread_routine, (void *) i);

	for (i = 0; i < NUM_THREADS; i++)
		pthread_join(tids[i], NULL);
	return 0;
}
