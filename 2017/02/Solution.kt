package `2017`.`02`

import java.io.File

private const val FILENAME = "2017/02/input.txt"
private val regex = Regex("""\s+""")

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
        .map { it -> it.split(regex).map { it.toInt() } }

    var result = 0

    for (line in file) {
        var smallest = Integer.MAX_VALUE
        var biggest = 0

        for (element in line) {
            if (element < smallest) {
                smallest = element
            }

            if (element > biggest) {
                biggest = element
            }
        }

        result += (biggest - smallest)
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()
        .map { it -> it.split(regex).map { it.toInt() } }

    var result = 0

    for (line in file) {
        val sorted = line.sortedDescending()

        outer@ for (i in sorted) {
            for (j in sorted.reversed()) {
                if (i == j) {
                    continue
                }

                if (i % j == 0) {
                    result += (i / j)
                    break@outer
                }
            }
        }
    }

    println(result)
}
