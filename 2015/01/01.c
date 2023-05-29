#include <stdio.h>

const char *FILE_NAME = "input.txt";

void part_one()
{
    FILE *file = fopen(FILE_NAME, "r");

    int floor = 0;
    int ch;
    while ((ch = fgetc(file)) != EOF)
    {
        if (ch == '(')
        {
            floor++;
        }
        else
        {
            floor--;
        }
    }

    fclose(file);

    printf("%d\n", floor);
}

void part_two()
{
    FILE *file = fopen(FILE_NAME, "r");

    int floor = 0;
    int index = 0;
    int ch;
    while ((ch = fgetc(file)) != EOF)
    {
        if (ch == '(')
        {
            floor++;
        }
        else
        {
            floor--;
        }

        if (floor == -1)
        {
            printf("%d\n", index + 1);
            break;
        }

        index++;
    }

    fclose(file);
}

int main()
{
    part_one();
    part_two();
    return 0;
}
