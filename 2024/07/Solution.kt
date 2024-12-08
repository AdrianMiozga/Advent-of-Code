package `2024`.`07`

import aoc.utils.pr
import java.io.File

private const val FILENAME = "2024/07/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var result = 0L

    for (line in file) {
        val (left, right) = line.split(": ")
        val numbers = right.split(" ").map { it.toLong() }.toMutableList()
        val combinations = combine(numbers.toMutableList(), operations = listOf("+", "*"))

        if (combinations.contains(left.toLong())) {
            result += left.toLong()
        }
    }

    pr(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var result = 0L

    for (line in file) {
        val (left, right) = line.split(": ")
        val numbers = right.split(" ").map { it.toLong() }.toMutableList()
        val combinations = combine(numbers.toMutableList())

        if (combinations.contains(left.toLong())) {
            result += left.toLong()
        }
    }

    pr(result)
}

private fun combine(
    numbers: MutableList<Long>,
    result: MutableSet<Long> = mutableSetOf<Long>(),
    currentOperation: String? = null,
    operations: List<String> = listOf("+", "*", "||"),
): Set<Long> {
    if (currentOperation != null) {
        val newNumber = when (currentOperation) {
            "+" -> numbers.removeFirst() + numbers.removeFirst()
            "*" -> numbers.removeFirst() * numbers.removeFirst()
            "||" -> (numbers.removeFirst().toString() + numbers.removeFirst().toString()).toLong()
            else -> throw IllegalStateException()
        }

        numbers.addFirst(newNumber)

        if (numbers.size == 1) {
            result.add(numbers.first())
            return result
        }
    }

    operations.forEach { currentOperation -> combine(numbers.toMutableList(), result, currentOperation, operations) }

    return result
}
