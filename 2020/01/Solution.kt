package `2020`.`01`

import java.io.File

private const val FILENAME = "2020/01/input.txt"

fun main() {
    partOne()
    partTwo()
}

/**
 * O(n^2)
 */
private fun partOne() {
    val lines = File(FILENAME).readLines().map(String::toInt)

    for (first in lines) {
        for (second in lines) {
            if (first + second == 2020) {
                println(first * second)
                return
            }
        }
    }
}

/**
 * O(n^3)
 */
private fun partTwo() {
    val lines = File(FILENAME).readLines().map(String::toInt)

    for (first in lines) {
        for (second in lines) {
            for (third in lines) {
                if (first + second + third == 2020) {
                    println(first * second * third)
                    return
                }
            }
        }
    }
}
