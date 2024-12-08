package template.kotlin

import java.io.File
import aoc.utils.*

private const val FILENAME = "YEAR/DAY/example.txt"

fun main() {
    partOne()
//    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    for (line in file) {
        println(line)
    }
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    for (line in file) {
        println(line)
    }
}
