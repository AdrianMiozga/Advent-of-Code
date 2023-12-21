FILENAME = "2015/02/input.txt"


def part_one():
    square_feet = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            length, width, height = map(int, line.strip().split("x"))
            square_feet += 2 * length * width + 2 * width * height + 2 * height * length

            slack = [length, width, height]
            slack.sort()
            square_feet += slack[0] * slack[1]

    print(square_feet)


def part_two():
    feet = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            length, width, height = map(int, line.strip().split("x"))
            # Bow
            feet += length * width * height

            wrapping = [length, width, height]
            wrapping.sort()
            feet += 2 * wrapping[0] + 2 * wrapping[1]

    print(feet)


if __name__ == "__main__":
    part_one()
    part_two()
