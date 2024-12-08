package `2022`.`05`

import java.io.File
import java.util.Stack

private const val FILENAME = "2022/05/input.txt"
private const val regex = """move (\d+) from (\d+) to (\d+)"""


fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    var separator = 0

    for ((index, line) in file.withIndex()) {
        if (line.isBlank()) {
            separator = index
            break
        }
    }

    val number = file[separator - 1].last().digitToInt()
    val stacks: MutableList<Stack<Char>> = MutableList(number) { Stack<Char>() }

    for (i in (separator - 2) downTo 0) {
        for ((index, character) in file[i].withIndex()) {
            if (character.isLetter()) {
                stacks[index / 4].push(character)
            }
        }
    }

    for (i in (separator + 1) until file.size) {
        val (amount, from, to) = Regex(regex).matchEntire(file[i])!!.destructured.toList()
            .map { it.toInt() }

        for (j in 0 until amount) {
            val value = stacks[from - 1].pop()
            stacks[to - 1].push(value)
        }
    }

    for (element in stacks) {
        print(element.peek())
    }
}

private fun partTwo() {
    val file = File(FILENAME).readLines()
    var separator = 0

    for ((index, line) in file.withIndex()) {
        if (line.isBlank()) {
            separator = index
            break
        }
    }

    val number = file[separator - 1].last().digitToInt()
    val stacks: MutableList<Stack<Char>> = MutableList(number) { Stack<Char>() }

    for (i in (separator - 2) downTo 0) {
        for ((index, character) in file[i].withIndex()) {
            if (character.isLetter()) {
                stacks[index / 4].push(character)
            }
        }
    }

    for (i in (separator + 1) until file.size) {
        val (amount, from, to) = Regex(regex).matchEntire(file[i])!!.destructured.toList()
            .map { it.toInt() }

        val chars = mutableListOf<Char>()

        for (j in 0 until amount) {
            chars.add(stacks[from - 1].pop())
        }

        stacks[to - 1].addAll(chars.reversed())
    }

    for (element in stacks) {
        print(element.peek())
    }
}
