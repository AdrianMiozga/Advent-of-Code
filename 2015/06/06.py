import re

FILENAME = "2015/06/input.txt"


def calculate():
    count_matrix = [[0] * 1000 for _ in range(1000)]
    brightness_matrix = [[0] * 1000 for _ in range(1000)]

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            x1, y1, x2, y2 = [int(x) for x in re.findall(r"\d+", line.strip())]

            for x in range(min(x1, x2), max(x1, x2) + 1):
                for y in range(min(y1, y2), max(y1, y2) + 1):
                    if "turn on" in line:
                        count_matrix[x][y] = 1
                        brightness_matrix[x][y] += 1
                    elif "turn off" in line:
                        count_matrix[x][y] = 0

                        if brightness_matrix[x][y] != 0:
                            brightness_matrix[x][y] -= 1
                    else:
                        count_matrix[x][y] = not count_matrix[x][y]
                        brightness_matrix[x][y] += 2

    count = 0
    brightness = 0

    for x in range(0, 1000):
        for y in range(0, 1000):
            if count_matrix[x][y] == 1:
                count += 1

            brightness += brightness_matrix[x][y]

    print(f"Count: {count}")
    print(f"Brightness: {brightness}")


if __name__ == "__main__":
    calculate()
