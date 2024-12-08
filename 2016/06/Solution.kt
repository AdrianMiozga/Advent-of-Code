package `2016`.`06`

import java.io.File

private const val FILENAME = "2016/06/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val transposed = transpose(file)

    for (line in transposed) {
        print(line.toList().groupingBy { it }.eachCount().maxBy { it.value }.key)
    }
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val transposed = transpose(file)

    for (line in transposed) {
        print(line.toList().groupingBy { it }.eachCount().minBy { it.value }.key)
    }
}

private fun transpose(list: List<String>): MutableList<String> {
    val result = mutableListOf<String>()

    for (column in list[0].indices) {
        var columnNew = ""

        for (row in list.indices) {
            columnNew += list[row][column]
        }

        result.add(columnNew)
    }

    return result
}
