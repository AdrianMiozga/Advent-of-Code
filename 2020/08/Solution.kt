package `2020`.`08`

import java.io.File
import java.util.List.copyOf

private const val FILENAME = "2020/08/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    var accumulator = 0

    var quit = false
    var index = 0

    val lines = File(FILENAME).readLines()
    val used = BooleanArray(lines.size)

    do {
        val instruction = lines[index]
        used[index] = true

        if (instruction.startsWith("nop")) {
            index++
        } else if (instruction.startsWith("acc")) {
            accumulator += instruction.split(" ")[1].toInt()
            index++
        } else {
            index += instruction.split(" ")[1].toInt()
        }

        if (used[index]) {
            quit = true
        }
    } while (!quit)

    println(accumulator)
}

private fun partTwo() {
    val lines = File(FILENAME).readLines()

    for ((lineIndex, line) in lines.withIndex()) {
        val alteredInput = copyOf(lines).toMutableList()

        if (line.startsWith("nop")) {
            alteredInput[lineIndex] =
                alteredInput[lineIndex].replace("nop", "jmp")
        } else if (line.startsWith("jmp")) {
            alteredInput[lineIndex] =
                alteredInput[lineIndex].replace("jmp", "nop")
        } else {
            continue
        }

        var accumulator = 0
        var index = 0
        var quit = false
        val used = BooleanArray(lines.size)

        do {
            val instruction = alteredInput[index]
            used[index] = true

            if (instruction.startsWith("nop")) {
                index++
            } else if (instruction.startsWith("acc")) {
                accumulator += instruction.split(" ")[1].toInt()
                index++
            } else {
                index += instruction.split(" ")[1].toInt()
            }

            if (index >= lines.size) {
                println(accumulator)
                return
            } else if (used[index]) {
                quit = true
            }
        } while (!quit)
    }
}
