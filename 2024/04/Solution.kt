package `2024`.`04`

import java.io.File

private const val FILENAME = "2024/04/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }

    var result = 0
    val accepted = listOf("XMAS", "SAMX")

    for (y in file.indices) {
        for (x in file[y].indices) {
            // Horizontal
            if (x + 3 < file[y].size) {
                val slice = file[y].slice(x..x + 3).joinToString(separator = "")

                if (slice in accepted) {
                    result++
                }
            }

            // Vertical
            if (y + 3 < file.size) {
                var string = ""

                for (dy in 0..3) {
                    string += file[y + dy][x]
                }

                if (string in accepted) {
                    result++
                }
            }

            // Diagonal right
            if (x + 3 < file[y].size && y + 3 < file.size) {
                var string = ""

                for (i in 0..3) {
                    string += file[y + i][x + i]
                }

                if (string in accepted) {
                    result++
                }
            }

            // Diagonal left
            if (x - 3 >= 0 && y + 3 < file.size) {
                var string = ""

                for (i in 0..3) {
                    string += file[y + i][x - i]
                }

                if (string in accepted) {
                    result++
                }
            }
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }

    var result = 0
    val accepted = listOf("MAS", "SAM")

    for (y in 1 until file.size - 1) {
        for (x in 1 until file[y].size - 1) {
            val string1 = "${file[y + 1][x - 1]}${file[y][x]}${file[y - 1][x + 1]}"
            val string2 = "${file[y + 1][x + 1]}${file[y][x]}${file[y - 1][x - 1]}"

            if (string1 in accepted && string2 in accepted) {
                result++
            }
        }
    }

    println(result)
}
