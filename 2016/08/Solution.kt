package `2016`.`08`

import java.io.File

private const val FILENAME = "2016/08/input.txt"

fun main() {
    partOneAndTwo()
}

private fun partOneAndTwo() {
    val instructions = File(FILENAME).readLines()

    val screen = MutableList(6) { MutableList(50) { '.' } }

    for (instruction in instructions) {
        if (instruction.startsWith("rect")) {
            val (width, height) = instruction.substringAfter(" ").split("x").map { it.toInt() }

            for (row in screen.indices) {
                for (column in screen[0].indices) {
                    if (row + 1 <= height && column + 1 <= width) {
                        screen[row][column] = '#'
                    }
                }
            }
        } else if (instruction.startsWith("rotate row")) {
            val (row, times) = Regex("""rotate row y=(\d+) by (\d+)""").matchEntire(instruction)!!.destructured.toList()
                .map { it.toInt() }

            repeat(times) {
                val temp = MutableList(screen[0].size) { '.' }

                for (i in 1 until temp.size) {
                    temp[i] = screen[row][i - 1]
                }

                temp[0] = screen[row].last()

                screen[row] = temp
            }
        } else {
            val (column, times) = Regex("""rotate column x=(\d+) by (\d+)""").matchEntire(instruction)!!.destructured.toList()
                .map { it.toInt() }

            repeat(times) {
                val temp = MutableList(screen.size) { '.' }

                for (i in 1 until temp.size) {
                    temp[i] = screen[i - 1][column]
                }

                temp[0] = screen.last()[column]

                for (i in screen.indices) {
                    screen[i][column] = temp[i]
                }
            }
        }
    }

    for (element in screen) {
        println(element)
    }

    var count = 0

    for (row in screen) {
        for (column in row) {
            if (column == '#') {
                count++
            }
        }
    }

    println(count)
}
