FILENAME = "2015/03/input.txt"


def part_one():
    santa_position = [0, 0]

    places = [[0, 0]]

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            for char in line:
                if char == ">":
                    santa_position[0] += 1
                elif char == "<":
                    santa_position[0] -= 1
                elif char == "^":
                    santa_position[1] += 1
                elif char == "v":
                    santa_position[1] -= 1

                places.append(list(santa_position))

    unique_places = []

    for place in places:
        if place not in unique_places:
            unique_places.append(place)

    print(len(unique_places))


def part_two():
    santa_position = [0, 0]
    robo_santa_position = [0, 0]

    santa = True

    places = [[0, 0]]

    with open(FILENAME, "r", encoding="UTF-8") as file:
        for line in file:
            for char in line:
                if char == ">":
                    if santa:
                        santa_position[0] += 1
                    else:
                        robo_santa_position[0] += 1
                elif char == "<":
                    if santa:
                        santa_position[0] -= 1
                    else:
                        robo_santa_position[0] -= 1
                elif char == "^":
                    if santa:
                        santa_position[1] += 1
                    else:
                        robo_santa_position[1] += 1
                elif char == "v":
                    if santa:
                        santa_position[1] -= 1
                    else:
                        robo_santa_position[1] -= 1

                if santa:
                    places.append(list(santa_position))
                else:
                    places.append(list(robo_santa_position))

                santa = not santa

    unique_places = []

    for place in places:
        if place not in unique_places:
            unique_places.append(place)

    print(len(unique_places))


if __name__ == "__main__":
    part_one()
    part_two()
