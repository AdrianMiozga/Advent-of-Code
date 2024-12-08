package `2017`.`05`

import java.io.File

private const val FILENAME = "2017/05/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it.toInt() }.toMutableList()

    var current = 0
    var steps = 0

    while (current < file.size) {
        val jump = file[current]

        file[current]++
        current += jump

        steps++
    }

    println(steps)
}

private fun partTwo() {
    val file = File(FILENAME).readLines().map { it.toInt() }.toMutableList()

    var current = 0
    var steps = 0

    while (current < file.size) {
        val jump = file[current]

        if (jump >= 3) {
            file[current]--
        } else {
            file[current]++
        }

        current += jump

        steps++
    }

    println(steps)
}
