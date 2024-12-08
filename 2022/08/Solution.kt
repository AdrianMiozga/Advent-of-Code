package `2022`.`08`

import java.io.File

private const val FILENAME = "2022/08/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it -> it.toCharArray().map { it.digitToInt() } }
    var result = file.size * 2 + (file.size - 2) * 2

    for (i in 1 until (file.size - 1)) {
        next@ for (j in 1 until (file[0].size - 1)) {
            for (left in (j - 1) downTo 0) {
                if (file[i][left] >= file[i][j]) {
                    break
                }

                if (left == 0) {
                    result++
                    continue@next
                }
            }

            for (right in (j + 1) until file[0].size) {
                if (file[i][right] >= file[i][j]) {
                    break
                }

                if (right == file[0].size - 1) {
                    result++
                    continue@next
                }
            }

            for (top in (i - 1) downTo 0) {
                if (file[top][j] >= file[i][j]) {
                    break
                }

                if (top == 0) {
                    result++
                    continue@next
                }
            }

            for (bottom in (i + 1) until file.size) {
                if (file[bottom][j] >= file[i][j]) {
                    break
                }

                if (bottom == file.size - 1) {
                    result++
                    continue@next
                }
            }
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines().map { it -> it.toCharArray().map { it.digitToInt() } }
    var result = 0

    for (i in 1 until (file.size - 1)) {
        for (j in 1 until (file[0].size - 1)) {
            var part = 1
            var inner = 0

            for (left in (j - 1) downTo 0) {
                inner++

                if (file[i][left] >= file[i][j]) {
                    break
                }
            }

            if (inner > 0) {
                part *= inner
                inner = 0
            }

            for (right in (j + 1) until file[0].size) {
                inner++

                if (file[i][right] >= file[i][j]) {
                    break
                }
            }

            if (inner > 0) {
                part *= inner
                inner = 0
            }

            for (top in (i - 1) downTo 0) {
                inner++

                if (file[top][j] >= file[i][j]) {
                    break
                }
            }

            if (inner > 0) {
                part *= inner
                inner = 0
            }

            for (bottom in (i + 1) until file.size) {
                inner++

                if (file[bottom][j] >= file[i][j]) {
                    break
                }
            }

            if (inner > 0) {
                part *= inner
            }

            if (result < part) {
                result = part
            }
        }
    }

    println(result)
}
