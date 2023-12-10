#include <Windows.h>

#define BUFFERSIZE 1024 * 7

const char *FILE_NAME = "input.txt";

void part_one()
{
    HANDLE hFile = CreateFileA(FILE_NAME, GENERIC_READ, FILE_SHARE_READ, NULL,
                               OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);

    if (hFile == INVALID_HANDLE_VALUE)
    {
        return;
    }

    char file[BUFFERSIZE];
    ReadFile(hFile, file, BUFFERSIZE - 1, NULL, NULL);
    CloseHandle(hFile);

    int floor = 0;

    for (int i = 0; i < BUFFERSIZE - 1; ++i)
    {
        if (file[i] == '\0')
        {
            break;
        }
        else if (file[i] == '(')
        {
            floor++;
        }
        else
        {
            floor--;
        }
    }

    printf("%d\n", floor);
}

void part_two()
{
    HANDLE hFile = CreateFileA(FILE_NAME, GENERIC_READ, FILE_SHARE_READ, NULL,
                               OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);

    if (hFile == INVALID_HANDLE_VALUE)
    {
        return;
    }

    char file[BUFFERSIZE];
    ReadFile(hFile, file, BUFFERSIZE - 1, NULL, NULL);
    CloseHandle(hFile);

    int floor = 0;

    for (int i = 0; i < BUFFERSIZE - 1; ++i)
    {
        if (file[i] == '\0')
        {
            break;
        }
        else if (file[i] == '(')
        {
            floor++;
        }
        else
        {
            floor--;
        }

        if (floor == -1)
        {
            printf("%d\n", i + 1);
            break;
        }
    }
}

int main()
{
    part_one();
    part_two();
    return 0;
}
