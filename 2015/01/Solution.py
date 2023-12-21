FILENAME = "2015/01/input.txt"


def part_one():
    floor = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            for char in line:
                if char == "(":
                    floor += 1
                elif char == ")":
                    floor -= 1

    print(floor)


def part_two():
    floor = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            for index, char in enumerate(line):
                if char == "(":
                    floor += 1
                elif char == ")":
                    floor -= 1

                if floor == -1:
                    print(index + 1)
                    return


if __name__ == "__main__":
    part_one()
    part_two()
