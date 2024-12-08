import os
import shutil
import sys
from datetime import datetime
from os.path import join
from urllib.error import HTTPError
from urllib.request import Request, urlopen


def main():
    try:
        with open("cookie.txt", "r", encoding="UTF-8") as file:
            session_cookie = file.read().strip()
    except FileNotFoundError:
        print(
            "Error: create ‘cookie.txt’ with session cookie from https://adventofcode.com"
        )

        sys.exit(1)

    if len(sys.argv) == 3:
        year = sys.argv[1]
        day = sys.argv[2].lstrip("0")
    else:
        now = datetime.now()
        year = str(now.year)
        day = str(now.day)

    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
        "Cookie": f"session={session_cookie}",
    }

    url = f"https://adventofcode.com/{year}/day/{day}/input"
    request = Request(url, headers=headers, method="GET")

    try:
        with urlopen(request) as response:
            response_body = response.read().decode("UTF-8")
    except HTTPError as exception:
        print(f"HTTP error: {exception.code} - {exception.reason}")
        sys.exit(1)

    padded_day = day.zfill(2)

    output_path = f"{year}/{padded_day}"

    # Create directories
    try:
        os.makedirs(output_path)
    except FileExistsError:
        print(f"Error: Directory {year}/{padded_day} already exists")
        sys.exit(1)

    # YEAR-README.md
    top_readme_path = join(year, "README.md")

    if not os.path.isfile(top_readme_path):
        shutil.copy("template/YEAR-README.md", top_readme_path)

        replace_in_file(
            top_readme_path,
            {
                "YEAR": year,
            },
        )

    # input.txt
    with open(join(output_path, "input.txt"), "w", encoding="UTF-8") as file:
        file.write(str(response_body).strip())

    # example.txt
    with open(join(output_path, "example.txt"), "w", encoding="UTF-8"):
        pass

    # README.md
    shutil.copy("template/README.md", output_path)

    replace_in_file(
        join(output_path, "README.md"),
        {
            "DAY": day,
            "YEAR": year,
        },
    )

    # Solution
    shutil.copytree("template/kotlin/", output_path, dirs_exist_ok=True)

    replace_in_file(
        join(output_path, "Solution.kt"),
        {
            "DAY": padded_day,
            "YEAR": year,
            "template": f"`{year}`",
            "kotlin": f"`{padded_day}`",
        },
    )


def replace_in_file(filepath, replacements):
    with open(filepath, "r", encoding="UTF-8") as file:
        content = file.read()

    for original, replacement in replacements.items():
        content = content.replace(original, replacement)

    with open(filepath, "w", encoding="UTF-8") as file:
        file.write(content)


if __name__ == "__main__":
    main()
