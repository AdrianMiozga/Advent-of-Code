FILENAME = "2022/01/input.txt"


def part_one():
    food = []
    counter = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            if line == "\n":
                food.append(counter)
                counter = 0
                continue

            counter += int(line.strip())

    print(max(food))


def part_two():
    food = []
    counter = 0

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            if line == "\n":
                food.append(counter)
                counter = 0
                continue

            counter += int(line.strip())

    print(sum(sorted(food, reverse=True)[:3]))


if __name__ == "__main__":
    part_one()
    part_two()
