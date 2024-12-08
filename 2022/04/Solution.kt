package `2022`.`04`

import java.io.File

private const val FILENAME = "2022/04/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var count = 0

    for (line in file) {
        val (firstX, firstY, secondX, secondY) = Regex("""(\d+)-(\d+),(\d+)-(\d+)""").matchEntire(line)!!.destructured.toList()
            .map { it.toInt() }

        if (secondX >= firstX && secondY <= firstY) {
            count++
        } else if (firstX >= secondX && firstY <= secondY) {
            count++
        }
    }

    println(count)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var count = 0

    for (line in file) {
        val (firstX, firstY, secondX, secondY) = Regex("""(\d+)-(\d+),(\d+)-(\d+)""").matchEntire(line)!!.destructured.toList()
            .map { it.toInt() }

        if (firstX in secondX..secondY) {
            count++
        } else if (firstY in secondX..secondY) {
            count++
        } else if (secondX in firstX..firstY) {
            count++
        } else if (secondY in firstX..firstY) {
            count++
        }
    }

    println(count)
}
