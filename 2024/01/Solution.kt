package `2024`.`01`

import java.io.File
import kotlin.math.abs

private const val FILENAME = "2024/01/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val leftArray = mutableListOf<Int>()
    val rightArray = mutableListOf<Int>()

    for (line in file) {
        val (left, right) = line.split(Regex("\\s+")).map { it.toInt() }
        leftArray.add(left)
        rightArray.add(right)
    }

    leftArray.sort()
    rightArray.sort()

    var result = 0

    for (i in 0 until leftArray.size) {
        result += abs(leftArray[i] - rightArray[i])
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val leftArray = mutableListOf<Int>()
    val occurrences = hashMapOf<Int, Int>()

    for (line in file) {
        val (left, right) = line.split(Regex("\\s+")).map { it.toInt() }

        leftArray.add(left)

        if (occurrences.containsKey(right)) {
            occurrences[right] = (occurrences[right]!! + 1)
        } else {
            occurrences[right] = 1
        }
    }

    var result = 0

    for (element in leftArray) {
        result += (element * occurrences.getOrDefault(element, 0))
    }

    print(result)
}
