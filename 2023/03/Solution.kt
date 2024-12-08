package `2023`.`03`

import java.io.File

private const val FILENAME = "2023/03/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }
    var result = 0

    for (row in 1 until file.size - 1) {
        for (column in 1 until file[row].size - 1) {
            if (file[row][column].isDigit() || file[row][column] == '.') {
                continue
            }

            for (windowRow in -1..1) {
                for (windowColumn in -1..1) {
                    val dRow = row - windowRow
                    val dColumn = column - windowColumn

                    if (file[dRow][dColumn].isDigit()) {
                        var number = ""
                        number = file[dRow][dColumn] + number
                        file[dRow][dColumn] = '.'

                        for (left in dColumn - 1 downTo 0) {
                            if (file[dRow][left].isDigit()) {
                                number = "${file[dRow][left]}$number"
                                file[dRow][left] = '.'
                            } else {
                                break
                            }
                        }

                        for (right in dColumn + 1 until file[dRow].size) {
                            if (file[dRow][right].isDigit()) {
                                number = "$number${file[dRow][right]}"
                                file[dRow][right] = '.'
                            } else {
                                break
                            }
                        }

                        result += number.toInt()
                    }
                }
            }
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }
    var result = 0

    for (row in 1 until file.size - 1) {
        for (column in 1 until file[row].size - 1) {
            if (file[row][column] != '*') {
                continue
            }

            var first = 0
            var second = 0

            for (windowRow in -1..1) {
                for (windowColumn in -1..1) {
                    val dRow = row - windowRow
                    val dColumn = column - windowColumn

                    if (file[dRow][dColumn].isDigit()) {
                        var number = ""
                        number = file[dRow][dColumn] + number
                        file[dRow][dColumn] = '.'

                        for (left in dColumn - 1 downTo 0) {
                            if (file[dRow][left].isDigit()) {
                                number = "${file[dRow][left]}$number"
                                file[dRow][left] = '.'
                            } else {
                                break
                            }
                        }

                        for (right in dColumn + 1 until file[dRow].size) {
                            if (file[dRow][right].isDigit()) {
                                number = "$number${file[dRow][right]}"
                                file[dRow][right] = '.'
                            } else {
                                break
                            }
                        }

                        if (first == 0) {
                            first = number.toInt()
                        } else {
                            second = number.toInt()
                        }
                    }
                }
            }

            if (first != 0 && second != 0) {
                result += (first * second)
            }
        }
    }

    println(result)
}
