# Part 1
score = 0

with open("02.txt", "r") as file:
    for line in file:
        opponent, me = line.split()

        if me == "X":
            score += 1
            me = "A"
        elif me == "Y":
            score += 2
            me = "B"
        else:
            score += 3
            me = "C"

        if (
            opponent == "A"
            and me == "B"
            or opponent == "B"
            and me == "C"
            or opponent == "C"
            and me == "A"
        ):
            score += 6
        elif opponent == me:
            score += 3

print(score)

# Part 2
score = 0

with open("02.txt", "r") as file:
    for line in file:
        opponent, me = line.split()

        if me == "X":
            me = "A"
        elif me == "Y":
            score += 3
            me = "B"
        else:
            score += 6
            me = "C"

        if (
            opponent == "C"
            and me == "C"
            or opponent == "A"
            and me == "B"
            or opponent == "B"
            and me == "A"
        ):
            score += 1
        elif (
            opponent == "C"
            and me == "A"
            or opponent == "A"
            and me == "C"
            or opponent == "B"
            and me == "B"
        ):
            score += 2
        elif (
            opponent == "A"
            and me == "A"
            or opponent == "B"
            and me == "C"
            or opponent == "C"
            and me == "B"
        ):
            score += 3

print(score)
