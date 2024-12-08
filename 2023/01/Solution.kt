package `2023`.`01`

import java.io.File
import kotlin.collections.iterator
import kotlin.math.max
import kotlin.math.min

private const val FILENAME = "2023/01/input.txt"
private val digitsRegex = Regex("""\D""")

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    var result = 0

    for (line in file) {
        val digits = line.replace(digitsRegex, "")
        result += "${digits.first()}${digits.last()}".toInt()
    }

    println(result)
}

private fun partTwo() {
    val mapping = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    val file = File(FILENAME).readLines()
    var result = 0

    for (line in file) {
        var firstDigit = ""
        var lastDigit = ""

        var index = 0
        while (firstDigit.isEmpty()) {
            val window = line.slice(0..min(index + 2, line.length - 1))
            val numbers = window.replace(digitsRegex, "")

            if (numbers.isNotEmpty()) {
                firstDigit = numbers.first().toString()
            } else {
                for (map in mapping) {
                    if (window.contains(map.key)) {
                        firstDigit = map.value
                    }
                }
            }

            index++
        }

        index = line.length
        while (lastDigit.isEmpty()) {
            val window = line.slice(max(index - 3, 0) until line.length)
            val numbers = window.replace(digitsRegex, "")

            if (numbers.isNotEmpty()) {
                lastDigit = numbers.last().toString()
            } else {
                for (map in mapping) {
                    if (window.contains(map.key)) {
                        lastDigit = map.value
                    }
                }
            }

            index--
        }

        result += (firstDigit + lastDigit).toInt()
    }

    println(result)
}
