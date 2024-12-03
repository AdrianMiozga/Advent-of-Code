import java.io.File

private const val FILENAME = "2024/03/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var result = 0

    for (line in file) {
        val foundInstructions = Regex("mul\\((\\d+),(\\d+)\\)").findAll(line)

        foundInstructions.forEach {
            result += (it.groupValues[1].toInt() * it.groupValues[2].toInt())
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var enabled = true
    var result = 0

    for (line in file) {
        for ((index, character) in line.withIndex()) {
            if (character == 'm') {
                val temp = line.substring(index).substringBefore(")", "")

                val matchResult = Regex("mul\\((\\d+),(\\d+)").matchEntire(temp)

                if (matchResult != null && enabled) {
                    result += (matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt())
                }
            } else if (character == 'd') {
                if (line.substring(index, index + 7) == "don't()") {
                    enabled = false
                } else if (line.substring(index, index + 4) == "do()") {
                    enabled = true
                }
            }
        }
    }

    println(result)
}
