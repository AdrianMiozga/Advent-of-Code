package `2020`.`05`

import java.io.File

private const val FILENAME = "2020/05/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val lines = File(FILENAME).readLines()

    var max = 0

    for (line in lines) {
        val rows = line.slice(0..6).toList()
        val row = bsp(0, 127, rows)

        val columns = line.slice(7 until line.length).toList()
        val column = bsp(0, 7, columns)

        val seatId = row * 8 + column

        if (seatId > max) {
            max = seatId
        }
    }

    println(max)
}

private fun partTwo() {
    val lines = File(FILENAME).readLines()

    val seats = mutableListOf<Int>()

    for (line in lines) {
        val rows = line.slice(0..6).toList()
        val row = bsp(0, 127, rows)

        val columns = line.slice(7 until line.length).toList()
        val column = bsp(0, 7, columns)

        val seatId = row * 8 + column
        seats.add(seatId)
    }

    seats.sort()

    for ((index, element) in seats.withIndex()) {
        if (seats.size - 1 == index) {
            break
        }

        if (element + 1 != seats[index + 1]) {
            println(element + 1)
        }
    }
}

private fun bsp(min: Int, max: Int, directions: List<Char>): Int {
    if (directions.size == 1) {
        return if (directions.first() in charArrayOf('F', 'L')) {
            min
        } else {
            max
        }
    }

    val half = (max - min) / 2
    val newDirections = directions.slice(1 until directions.size)

    return if (directions.first() in charArrayOf('F', 'L')) {
        bsp(min, min + half, newDirections)
    } else {
        bsp(min + half + 1, max, newDirections)
    }
}
