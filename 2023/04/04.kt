import java.io.File
import kotlin.math.pow

private const val FILENAME = "2023/04/input.txt"
private val regex = Regex("""Card\s+\d+:\s+""")

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var result = 0

    for (line in file) {
        val withoutPrefix = regex.replace(line, "")

        val (winningNumbers, numbers) = withoutPrefix.split(" | ")
            .map { it.split(Regex("""\s+""")) }

        var counter = 0

        for (number in numbers) {
            for (win in winningNumbers) {
                if (win == number) {
                    counter++
                    break
                }
            }
        }

        result += 2.0.pow(counter - 1).toInt()
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val list = IntArray(file.size) { 1 }

    for ((index, line) in file.withIndex()) {
        val withoutPrefix = regex.replace(line, "")

        val (winningNumbers, numbers) = withoutPrefix.split(" | ")
            .map { it.split(Regex("""\s+""")) }

        var counter = 0

        for (number in numbers) {
            for (win in winningNumbers) {
                if (win == number) {
                    counter++
                    break
                }
            }
        }

        for (i in 0 until counter) {
            list[index + 1 + i] += list[index]
        }
    }

    println(list.sum())
}
