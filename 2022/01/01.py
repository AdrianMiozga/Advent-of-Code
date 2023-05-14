food = []
counter = 0

with open("01.txt", "r") as file:
    for line in file:
        if line == "\n":
            food.append(counter)
            counter = 0
            continue

        counter += int(line.strip())

# Part 1
print(max(food))

# Part 2
print(sum(sorted(food, reverse=True)[:3]))
