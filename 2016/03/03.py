FILENAME = "2016/03/input.txt"


def part_one():
    possible_triangles = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            sides = sorted(list(map(int, line.split())))

            if sides[0] + sides[1] > sides[2]:
                possible_triangles += 1

    print(possible_triangles)


def part_two():
    matrix = []

    for i in range(0, 3):
        with open(FILENAME, "r", encoding="UTF-8") as file:
            for line in file:
                matrix += [int(line.split()[i])]

    possible_triangles = 0

    for i in range(0, len(matrix), 3):
        sides = sorted(matrix[i : i + 3])

        if sides[0] + sides[1] > sides[2]:
            possible_triangles += 1

    print(possible_triangles)


if __name__ == "__main__":
    part_one()
    part_two()
