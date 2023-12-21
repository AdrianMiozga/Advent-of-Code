import java.io.File
import kotlin.math.min

private const val FILENAME = "2023/13/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file =
        File(FILENAME).readText().split("\r\n\r\n").map { it.split("\r\n") }
    var result = 0

    for (pattern in file) {
        val transposedPattern = transpose(pattern)

        val rows = getStartingPoints(pattern, 0)
        val columns = getStartingPoints(transposedPattern, 0)

        result += count(columns, transposedPattern)
        result += (count(rows, pattern) * 100)
    }

    println(result)
}

private fun transpose(list: List<String>): MutableList<String> {
    val transposedPattern = mutableListOf<String>()

    for (column in 0 until list[0].length) {
        var currentColumn = ""

        for (row in list.indices) {
            currentColumn += list[row][column]
        }

        transposedPattern.add(currentColumn)
    }

    return transposedPattern
}

private fun count(
    elements: List<Int>,
    pattern: List<String>,
): Int {
    var result = 0

    for (element in elements) {
        var valid = true

        val max = min(element - 2, pattern.size - element - 2)

        for (i in 0..max) {
            if (pattern[element - 2 - i] != pattern[element + 1 + i]) {
                valid = false
            }
        }

        if (valid) {
            result += element
        }
    }

    return result
}

private fun partTwo() {
    val file =
        File(FILENAME).readText().split("\r\n\r\n").map { it.split("\r\n") }
    var result = 0

    for (pattern in file) {
        val transposedPattern = transpose(pattern)

        var rows = getStartingPoints(pattern, 0)
        var columns = getStartingPoints(transposedPattern, 0)

        result += countWithSmudge(columns, transposedPattern)
        result += (countWithSmudge(rows, pattern) * 100)

        rows = getStartingPoints(pattern, 1)
        columns = getStartingPoints(transposedPattern, 1)

        result += count(columns, transposedPattern)
        result += (count(rows, pattern) * 100)
    }

    println(result)
}

private fun stringDifference(first: String, second: String): Int {
    var same = 0

    for ((index, character) in first.withIndex()) {
        if (character == second[index]) {
            same++
        }
    }

    return first.length - same
}

private fun countWithSmudge(
    elements: List<Int>,
    pattern: List<String>,
): Int {
    var result = 0

    for (element in elements) {
        var oneCharDifference = true
        var exact = true

        val max = min(element - 2, pattern.size - element - 2)

        for (i in 0..max) {
            val up = element - 2 - i
            val down = element + 1 + i

            if (stringDifference(pattern[up], pattern[down]) == 1) {
                exact = false
            }

            if (stringDifference(pattern[up], pattern[down]) > 1) {
                oneCharDifference = false
                break
            }
        }

        if (oneCharDifference && !exact) {
            result += element
        }
    }

    return result
}

private fun getStartingPoints(pattern: List<String>, difference: Int): List<Int> {
    var previousRow = pattern[0]
    val list = mutableListOf<Int>()

    for (index in difference until pattern.size) {
        if (stringDifference(previousRow, pattern[index]) == difference) {
            list.add(index)
        }

        previousRow = pattern[index]
    }

    return list
}
