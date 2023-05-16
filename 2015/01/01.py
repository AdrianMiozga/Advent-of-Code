opening_bracket = 0
closing_bracket = 0

with open("input.txt", "r") as file:
    for line in file:
        for ch in line:
            if ch == "(":
                opening_bracket += 1
            elif ch == ")":
                closing_bracket += 1

floor = opening_bracket - closing_bracket

print(floor)
