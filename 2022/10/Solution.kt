package `2022`.`10`

import java.io.File

private const val FILENAME = "2022/10/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var x = 1
    var cycle = 1
    var line = 0
    var secondTime = false
    var result = 0

    while (cycle < 220) {
        cycle++

        if (file[line] == "noop") {
            line++
        } else {
            if (secondTime) {
                x += file[line].split(" ").last().toInt()
                line++
                secondTime = false
            } else {
                secondTime = true
            }
        }

        if (line >= file.size) {
            line = 0
        }

        if ((cycle - 20) % 40 == 0) {
            result += cycle * x
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var x = 1
    var cycle = 0
    var line = 0
    var secondTime = false
    var result = ""

    while (cycle < 240) {
        val cycleMod = cycle % 40

        if (cycle != 0 && cycleMod == 0) {
            result += "\n"
        }

        result += if (x in (cycleMod - 1)..(cycleMod + 1)) {
            "#"
        } else {
            "."
        }

        cycle++

        if (file[line] == "noop") {
            line++
        } else {
            if (secondTime) {
                x += file[line].split(" ").last().toInt()
                line++
                secondTime = false
            } else {
                secondTime = true
            }
        }

        if (line >= file.size) {
            line = 0
        }
    }

    println(result)
}
