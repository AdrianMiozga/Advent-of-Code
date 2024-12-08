package `2023`.`02`

import java.io.File

private const val FILENAME = "2023/02/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    var result = 0

    for (line in file) {
        val regex = Regex("""Game (\d+): """)
        val gameID = regex.find(line)!!.groupValues[1].toInt()

        val sets = line.split(";")
        var valid = true

        for (set in sets) {
            val red =
                Regex("""(\d+) red""").find(set)?.groupValues?.get(1)?.toInt()
                    ?: 0

            val green =
                Regex("""(\d+) green""").find(set)?.groupValues?.get(1)?.toInt()
                    ?: 0

            val blue =
                Regex("""(\d+) blue""").find(set)?.groupValues?.get(1)?.toInt()
                    ?: 0

            if (red > 12 || green > 13 || blue > 14) {
                valid = false
                break
            }
        }

        if (valid) {
            result += gameID
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()
    var result = 0

    for (line in file) {
        var minRed = 1
        var minGreen = 1
        var minBlue = 1

        val sets = line.split(";")

        for (set in sets) {
            val red =
                Regex("""(\d+) red""").find(set)?.groupValues?.get(1)?.toInt()
                    ?: 1

            val green =
                Regex("""(\d+) green""").find(set)?.groupValues?.get(1)?.toInt()
                    ?: 1

            val blue =
                Regex("""(\d+) blue""").find(set)?.groupValues?.get(1)?.toInt()
                    ?: 1

            if (minRed < red) {
                minRed = red
            }

            if (minGreen < green) {
                minGreen = green
            }

            if (minBlue < blue) {
                minBlue = blue
            }
        }

        result += (minRed * minGreen * minBlue)
    }

    println(result)
}
