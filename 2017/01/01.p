#include <file>

new filename{} = "input.txt"

main() {
    part_one()
    part_two()
}

part_one() {
    new File: file = fopen(filename, io_read)

    if (!file) {
        return
    }

    new first_char{} = ""
    new previous_char{} = ""
    new result = 0

    for (;;) {
        new current_char = fgetchar(file)

        if (current_char == EOF) {
            break
        }

        if (first_char == "") {
            first_char{0} = current_char
        }

        if (previous_char != "") {
            if (previous_char{0} == current_char) {
                result += (current_char - 48)
            }
        }

        previous_char{0} = current_char
    }

    if (first_char{0} == previous_char{0}) {
        result += (first_char{0} - 48)
    }

    printf("%d\n", result)

    fclose(file)
}

part_two() {
    new array[2052] = []
    new File: file = fopen(filename, io_read)

    if (!file) {
        return
    }

    new result = 0
    new length = flength(file)

    for (new i = 0; ; ++i) {
        new current_char = fgetchar(file)

        if (current_char == EOF) {
            break
        }

        array[i] = current_char
    }

    for (new i = 0; i < length; ++i) {
        new next = (i + (length / 2)) % length

        if (array[i] == array[next]) {
            result += (array[i] - 48)
        }
    }

    printf("%d\n", result)
    fclose(file)
}
