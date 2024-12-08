package `2015`.`08`

import java.io.File
import kotlin.text.iterator

private const val FILENAME = "2015/08/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var result = 0

    for (line in file) {
        var dataCharacters = 0

        var index = 0

        while (index < line.length) {
            if (line[index] == '\\') {
                dataCharacters++

                val nextCharacter = line[index + 1]

                when (nextCharacter) {
                    '"' -> index += 2
                    '\\' -> index += 2
                    'x' -> index += 4
                }
            } else if (line[index] == '"') {
                index++
            } else {
                index++
                dataCharacters++
            }
        }

        result += line.length
        result -= dataCharacters
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var result = 0

    for (line in file) {
        var encodedCharacters = 2

        for (character in line) {
            if (character in listOf('"', '\\')) {
                encodedCharacters += 2
            } else {
                encodedCharacters++
            }
        }

        result += encodedCharacters
        result -= line.length
    }

    println(result)
}
