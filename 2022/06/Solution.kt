import java.io.File

private const val FILENAME = "2022/06/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    calculate(4)
}

private fun partTwo() {
    calculate(14)
}

private fun calculate(number: Int) {
    val file = File(FILENAME).readLines()

    nextLine@ for (line in file) {
        for (i in line.indices) {
            if (line.subSequence(i, i + number).toList()
                    .distinct().size != number
            ) {
                continue
            } else {
                println(i + number)
                continue@nextLine
            }
        }
    }
}
