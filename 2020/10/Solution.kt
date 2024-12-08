package `2020`.`10`

import java.io.File

private const val FILENAME = "2020/10/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val adapters =
        File(FILENAME).readLines().map { it.toInt() }.sorted().toMutableList()

    // Add device's built-in joltage adapter
    adapters.addLast(adapters.last() + 3)

    var jolts = 0
    val differences: IntArray = intArrayOf(0, 0, 0)

    for (rating in adapters) {
        val difference = rating - jolts

        differences[difference - 1]++
        jolts += difference
    }

    println(differences[0] * differences[2])
}

private fun partTwo() {
    val adapters =
        File(FILENAME).readLines().map { it.toInt() }.sorted().toMutableList()

    // Add charging outlet
    adapters.addFirst(0)

    // Add device's built-in joltage adapter
    adapters.addLast(adapters.last() + 3)

    var result = 1L
    var index = 0

    while (index < adapters.size - 1) {
        var innerIndex = index + 1
        var step = 1

        while (adapters[innerIndex] == adapters[index] + innerIndex - index) {
            step++
            innerIndex++
        }

        index += step

        when (step) {
            3 -> {
                result *= 2
            }

            4 -> {
                result *= 4
            }

            5 -> {
                result *= 7
            }
        }
    }

    println(result)
}
