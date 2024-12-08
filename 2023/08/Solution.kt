package `2023`.`08`

import java.io.File
import kotlin.collections.iterator

private const val FILENAME = "2023/08/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val instructions = file[0]
    var instructionIndex = 0
    var steps = 0

    val map: MutableMap<String, Pair<String, String>> = mutableMapOf()
    var currentNode = "AAA"

    for (line in file.slice(2 until file.size)) {
        val (input, left, right) = Regex("""(\w+) = \((\w+), (\w+)\)""").matchEntire(
            line
        )!!.destructured

        map[input] = Pair(left, right)
    }

    while (currentNode != "ZZZ") {
        currentNode = if (instructions[instructionIndex] == 'L') {
            map[currentNode]!!.first
        } else {
            map[currentNode]!!.second
        }

        steps++
        instructionIndex = (instructionIndex + 1) % instructions.length
    }

    println(steps)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val instructions = file[0]
    var instructionIndex = 0

    val map: MutableMap<String, Pair<String, String>> = mutableMapOf()
    val full: MutableMap<String, Pair<String, String>> = mutableMapOf()

    for (line in file.slice(2 until file.size)) {
        val (input, left, right) = Regex("""(\w+) = \((\w+), (\w+)\)""").matchEntire(
            line
        )!!.destructured

        full[input] = Pair(left, right)

        if (input.endsWith("A")) {
            map[input] = Pair(left, right)
        }
    }

    val listSteps = MutableList(map.size) { 0 }
    var index = 0

    for (element in map) {
        var steps = 0
        var current = element.key

        while (true) {
            current = if (instructions[instructionIndex] == 'L') {
                full[current]!!.first
            } else {
                full[current]!!.second
            }

            steps++
            instructionIndex = (instructionIndex + 1) % instructions.length

            if (current.endsWith("Z")) {
                break
            }
        }

        listSteps[index] = steps
        index++
    }

    println(findLCM(listSteps))
}

private fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger

    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }

        lcm += larger
    }

    return maxLcm
}

private fun findLCM(numbers: List<Int>): Long {
    var result = numbers[0].toLong()

    for (i in 1 until numbers.size) {
        result = findLCM(result, numbers[i].toLong())
    }

    return result
}
