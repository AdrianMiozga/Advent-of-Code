santa_position = [0, 0]
robo_santa_position = [0, 0]

santa = True

places = [[0, 0]]

with open("03.txt", "r") as file:
    for line in file:
        for ch in line:
            if ch == ">":
                if santa:
                    santa_position[0] += 1
                else:
                    robo_santa_position[0] += 1
            elif ch == "<":
                if santa:
                    santa_position[0] -= 1
                else:
                    robo_santa_position[0] -= 1
            elif ch == "^":
                if santa:
                    santa_position[1] += 1
                else:
                    robo_santa_position[1] += 1
            elif ch == "v":
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

for x in places:
    if x not in unique_places:
        unique_places.append(x)

print(unique_places)
print(len(places))
print(len(unique_places))
