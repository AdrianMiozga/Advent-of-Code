import java.io.File

private const val FILENAME = "2016/02/input.txt"

private val partOne: Array<String> = arrayOf(
    "123",
    "456",
    "789",
)

private val partTwo: Array<String> = arrayOf(
    "  1  ",
    " 234 ",
    "56789",
    " ABC ",
    "  D  ",
)

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    var x = 1
    var y = 1
    var result = ""
    val lines = File(FILENAME).readLines()

    for (line in lines) {
        for (direction in line) {
            if (direction == 'U') {
                if (y != 0) {
                    y -= 1
                }
            } else if (direction == 'D') {
                if (y != 2) {
                    y += 1
                }
            } else if (direction == 'L') {
                if (x != 0) {
                    x -= 1
                }
            } else if (direction == 'R') {
                if (x != 2) {
                    x += 1
                }
            }
        }

        result += partOne[y][x]
    }

    println(result)
}

private fun partTwo() {
    var x = 0
    var y = 2
    var result = ""
    val lines = File(FILENAME).readLines()

    for (line in lines) {
        for (direction in line) {
            if (direction == 'U') {
                if (y != 0 && partTwo[y - 1][x] != ' ') {
                    y -= 1
                }
            } else if (direction == 'D') {
                if (y != 4 && partTwo[y + 1][x] != ' ') {
                    y += 1
                }
            } else if (direction == 'L') {
                if (x != 0 && partTwo[y][x - 1] != ' ') {
                    x -= 1
                }
            } else if (direction == 'R') {
                if (x != 4 && partTwo[y][x + 1] != ' ') {
                    x += 1
                }
            }
        }

        result += partTwo[y][x]
    }

    println(result)
}
