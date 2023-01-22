# Part I
possible_triangles = 0

with open("03.txt", "r") as file:
    for line in file:
        sides = sorted(list(map(int, line.split())))

        if sides[0] + sides[1] > sides[2]:
            possible_triangles += 1

print(f"Part I: {possible_triangles}")

# Part II
matrix = []

for i in range(0, 3):
    with open("03.txt", "r") as file:
        for line in file:
            matrix += [int(line.split()[i])]

possible_triangles = 0

for i in range(0, len(matrix), 3):
    sides = sorted(matrix[i : i + 3])

    if sides[0] + sides[1] > sides[2]:
        possible_triangles += 1

print(f"Part II: {possible_triangles}")
