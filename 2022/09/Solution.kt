package `2022`.`09`

import java.io.File
import kotlin.math.abs

private const val FILENAME = "2022/09/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val head = mutableListOf(Pair(0, 0))
    val tail = mutableListOf(Pair(0, 0))

    for (line in file) {
        val (direction, times) = line.split(" ")

        repeat(Integer.parseInt(times)) {
            val newPosition = when (direction) {
                "R" -> Pair(head.last().first + 1, head.last().second)
                "L" -> Pair(head.last().first - 1, head.last().second)
                "U" -> Pair(head.last().first, head.last().second + 1)
                "D" -> Pair(head.last().first, head.last().second - 1)
                else -> throw IllegalStateException()
            }

            head.add(newPosition)

            val dx = head.last().first - tail.last().first
            val dy = head.last().second - tail.last().second

            var distance = abs(dx) + abs(dy)
            val diagonal = dx != 0 && dy != 0

            if (diagonal) {
                distance -= 1
            }

            if (distance > 1) {
                tail.add(head[head.size - 2])
            }
        }
    }

    println(tail.toHashSet().size)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val knots = List(10) { mutableListOf(Pair(0, 0)) }

    for (line in file) {
        val (direction, times) = line.split(" ")

        repeat(Integer.parseInt(times)) {
            val newPosition = when (direction) {
                "R" -> Pair(knots.first().last().first + 1, knots.first().last().second)
                "L" -> Pair(knots.first().last().first - 1, knots.first().last().second)
                "U" -> Pair(knots.first().last().first, knots.first().last().second + 1)
                "D" -> Pair(knots.first().last().first, knots.first().last().second - 1)
                else -> throw IllegalStateException()
            }

            knots.first().add(newPosition)

            for (i in 1 until knots.size) {
                val dx = knots[i - 1].last().first - knots[i].last().first
                val dy = knots[i - 1].last().second - knots[i].last().second

                var distance = abs(dx) + abs(dy)
                val diagonal = dx != 0 && dy != 0

                if (diagonal) {
                    distance -= 1

                    if (distance > 1) {
                        knots[i].add(Pair(knots[i].last().first + (dx / abs(dx)), knots[i].last().second + (dy / abs(dy))))
                    }
                } else if (distance > 1) {
                    if (dx != 0) {
                        knots[i].add(Pair(knots[i].last().first + (dx / abs(dx)), knots[i].last().second))
                    } else {
                        knots[i].add(Pair(knots[i].last().first, knots[i].last().second + (dy / abs(dy))))
                    }
                }
            }
        }
    }

    println(knots.last().toHashSet().size)
}
