package `2020`.`11`

import java.io.File

private const val FILENAME = "2020/11/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun same(
    firstList: List<CharArray>,
    secondList: List<CharArray>
): Boolean {
    for (row in firstList.indices) {
        for (column in 0 until firstList[0].size) {
            if (firstList[row][column] != secondList[row][column]) {
                return false
            }
        }
    }

    return true
}

private fun occupiedSeats(list: List<CharArray>): Int {
    var counter = 0

    for (row in list) {
        for (element in row) {
            if (element == '#') {
                counter++
            }
        }
    }

    return counter
}

private fun partOne() {
    val currentLayout = File(FILENAME).readLines().map { it.toCharArray() }

    do {
        val previousLayout = currentLayout.map { it.clone() }

        for ((rowIndex, row) in previousLayout.withIndex()) {
            for ((columnIndex, element) in row.withIndex()) {
                if (element == '.') {
                    continue
                }

                var seatsOccupied = 0

                for (i in -1..1) {
                    for (j in -1..1) {
                        val dx = columnIndex + i
                        val dy = rowIndex + j

                        if (dx < 0 || dy < 0 || dy >= previousLayout.size || dx >= previousLayout[0].size) {
                            continue
                        }

                        if (dx == columnIndex && dy == rowIndex) {
                            continue
                        }

                        if (previousLayout[dy][dx] == '#') {
                            seatsOccupied++
                        }
                    }
                }

                if (previousLayout[rowIndex][columnIndex] == 'L' && seatsOccupied == 0) {
                    currentLayout[rowIndex][columnIndex] = '#'
                }

                if (previousLayout[rowIndex][columnIndex] == '#' && seatsOccupied >= 4) {
                    currentLayout[rowIndex][columnIndex] = 'L'
                }
            }
        }
    } while (!same(previousLayout, currentLayout))

    println(occupiedSeats(currentLayout))
}

private fun partTwo() {
    val currentLayout = File(FILENAME).readLines().map { it.toCharArray() }

    do {
        val previousLayout = currentLayout.map { it.clone() }

        for ((rowIndex, row) in previousLayout.withIndex()) {
            for ((columnIndex, element) in row.withIndex()) {
                if (element == '.') {
                    continue
                }

                var occupiedSeats = 0

                val directions = arrayListOf(
                    Pair(1, 0),
                    Pair(-1, 0),
                    Pair(0, 1),
                    Pair(0, -1),
                    Pair(1, 1),
                    Pair(1, -1),
                    Pair(-1, 1),
                    Pair(-1, -1)
                )

                for (direction in directions) {
                    var dx = rowIndex + direction.first
                    var dy = columnIndex + direction.second

                    do {
                        if (dx < 0 || dy < 0 || dx >= previousLayout.size || dy >= previousLayout[0].size) {
                            break
                        }

                        if (previousLayout[dx][dy] == 'L') {
                            break
                        } else if (previousLayout[dx][dy] == '#') {
                            occupiedSeats++
                            break
                        }

                        dx += direction.first
                        dy += direction.second
                    } while(true)
                }

                if (previousLayout[rowIndex][columnIndex] == 'L' && occupiedSeats == 0) {
                    currentLayout[rowIndex][columnIndex] = '#'
                }

                if (previousLayout[rowIndex][columnIndex] == '#' && occupiedSeats >= 5) {
                    currentLayout[rowIndex][columnIndex] = 'L'
                }
            }
        }
    } while (!same(previousLayout, currentLayout))

    println(occupiedSeats(currentLayout))
}
