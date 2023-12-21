FILENAME = "2016/01/input.txt"


def part_one():
    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            x = 0
            y = 0
            angle = 0

            for m in line.strip().split(", "):
                steps = int(m[1:])

                if m[0] == "R":
                    angle += 90

                    if angle == 360:
                        angle = 0
                else:
                    angle -= 90

                    if angle < 0:
                        angle = 270

                if angle == 0:
                    y += steps
                elif angle == 90:
                    x += steps
                elif angle == 180:
                    y -= steps
                else:
                    x -= steps

            print(abs(x) + abs(y))


def part_two():
    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            x = 0
            y = 0
            angle = 0
            twice = False
            visited_places = []

            for m in line.strip().split(", "):
                steps = int(m[1:])

                if m[0] == "R":
                    angle += 90

                    if angle == 360:
                        angle = 0
                else:
                    angle -= 90

                    if angle < 0:
                        angle = 270

                for _ in range(steps):
                    if angle == 0:
                        y += 1
                    elif angle == 90:
                        x += 1
                    elif angle == 180:
                        y -= 1
                    else:
                        x -= 1

                    if [x, y] in visited_places and twice is False:
                        twice = True
                        print(abs(x) + abs(y))

                    visited_places.append([x, y])


if __name__ == "__main__":
    part_one()
    part_two()
