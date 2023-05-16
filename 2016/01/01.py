# Part I
with open("input.txt", "r") as file:
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

# Part II
with open("input.txt", "r") as file:
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

            for i in range(steps):
                if angle == 0:
                    y += 1
                elif angle == 90:
                    x += 1
                elif angle == 180:
                    y -= 1
                else:
                    x -= 1

                if [x, y] in visited_places and twice == False:
                    twice = True
                    print(f"Twice: {abs(x) + abs(y)}")

                visited_places.append([x, y])
