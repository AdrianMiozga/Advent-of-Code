import hashlib

FILENAME = "2015/04/input.txt"


def part_one():
    with open(FILENAME, "r", encoding="UTF-8") as file:
        key = file.readline()

    i = 0

    while True:
        i += 1

        test = key + str(i)
        result = hashlib.md5(test.encode("UTF-8")).hexdigest()

        if result.startswith("00000"):
            print(i)
            break


def part_two():
    with open(FILENAME, "r", encoding="UTF-8") as file:
        key = file.readline()

    i = 0

    while True:
        i += 1

        test = key + str(i)
        result = hashlib.md5(test.encode("UTF-8")).hexdigest()

        if result.startswith("000000"):
            print(i)
            break


if __name__ == "__main__":
    part_one()
    part_two()
