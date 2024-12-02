import java.io.File
import kotlin.math.abs

private const val FILENAME = "2024/02/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var result = 0

    for (report in file) {
        val levels = report.split(" ").map { it.toInt() }

        var safe = true
        val decreasing = levels[1] - levels[0] < 0

        for (i in 1 until levels.size) {
            val order = levels[i] - levels[i - 1] < 0
            val difference = abs(levels[i] - levels[i - 1])

            if (order != decreasing || difference !in 1..3) {
                safe = false
                break
            }
        }

        if (safe) {
            result++
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var result = 0

    nextReport@for (report in file) {
        val levels = report.split(" ").map { it.toInt() }
        val arrays = mutableListOf(levels)

        for (i in levels.indices) {
            val clone = levels.toMutableList()
            clone.removeAt(i)
            arrays.add(clone)
        }

        for (array in arrays) {
            var safe = true
            val decreasing = array[1] - array[0] < 0

            for (i in 1 until array.size) {
                val order = array[i] - array[i - 1] < 0
                val difference = abs(array[i] - array[i - 1])

                if (order != decreasing || difference !in 1..3) {
                    safe = false
                    break
                }
            }

            if (safe) {
                result++
                continue@nextReport
            }
        }
    }

    println(result)
}
