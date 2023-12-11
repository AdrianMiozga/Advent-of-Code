import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private const val FILENAME = "2023/11/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    calculate(2)
}

private fun partTwo() {
    calculate(1_000_000)
}

private fun calculate(expandAmount: Int) {
    val file =
        File(FILENAME).readLines().map { it.toMutableList() }.toMutableList()

    val expandRows = mutableListOf<Int>()

    for ((index, line) in file.withIndex()) {
        if (line.all { it == '.' }) {
            expandRows.add(index)
        }
    }

    val expandColumns = mutableListOf<Int>()

    for (column in 0 until file[0].size) {
        var all = true

        for (row in 0 until file.size) {
            if (file[row][column] != '.') {
                all = false
                break
            }
        }

        if (all) {
            expandColumns.add(column)
        }
    }

    val galaxies = mutableListOf<Pair<Int, Int>>()

    for ((indexRow, row) in file.withIndex()) {
        for ((columnRow, element) in row.withIndex()) {
            if (element == '#') {
                galaxies.add(Pair(indexRow, columnRow))
            }
        }
    }

    var result = 0L

    for (i in 0 until galaxies.size) {
        for (j in i + 1 until galaxies.size) {
            val minX = min(galaxies[i].first, galaxies[j].first)
            val maxX = max(galaxies[i].first, galaxies[j].first)
            val minY = min(galaxies[i].second, galaxies[j].second)
            val maxY = max(galaxies[i].second, galaxies[j].second)

            val new = Pair(maxX - minX, maxY - minY)

            var count = 0
            for (element in expandRows) {
                if (element in (minX + 1) until maxX) {
                    count++
                }
            }

            for (element in expandColumns) {
                if (element in (minY + 1) until maxY) {
                    count++
                }
            }

            result += (new.first + new.second + (count * expandAmount) - count)
        }
    }

    println(result)
}
