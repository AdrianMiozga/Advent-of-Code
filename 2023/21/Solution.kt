package `2023`.`21`

import java.io.File
import java.util.*

private const val FILENAME = "2023/21/input.txt"

val directions = listOf(
    Pair(-1, 0),
    Pair(1, 0),
    Pair(0, -1),
    Pair(0, 1),
)

fun main() {
    partOne()
}

private fun partOne() {
    val map = File(FILENAME).readLines()

    var start = Pair(0, 0)

    start@for ((row, line) in map.withIndex()) {
        for ((column, character) in line.withIndex()) {
            if (character == 'S') {
                start = Pair(column, row)
                break@start
            }
        }
    }

    val queue = LinkedList<Pair<Int, Int>>()
    queue.push(start)

    repeat(64) {
        repeat(queue.size) {
            val position = queue.remove()

            for (direction in directions) {
                val newX = position.first + direction.first
                val newY = position.second + direction.second

                if (newX < 0 || newY < 0) {
                    continue
                }

                if (newX >= map[0].length || newY >= map.size) {
                    continue
                }

                if (map[newY][newX] in listOf('.', 'S')) {
                    if (!queue.contains(Pair(newX, newY))) {
                        queue.add(Pair(newX, newY))
                    }
                }
            }
        }
    }

    println(queue.size)
}
