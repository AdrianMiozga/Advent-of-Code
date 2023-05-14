import hashlib

input = "yzbqklnj"
i = 0

while True:
    i += 1

    test = input + str(i)
    result = hashlib.md5(test.encode("utf-8")).hexdigest()

    if result.startswith("000000"):
        print(i)
        break
