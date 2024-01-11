import java.io.File

private const val FILENAME = "2016/09/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    for (line in file) {
        var index = 0

        var count = 0

        while (index < line.length) {
            if (line[index] == '(') {
                val end = line.indexOf(')', index)

                val (x, y) = line.substring((index + 1)..<end).split("x").map { it.toInt() }

                count += (x * y)
                index = (end + x + 1)
            } else {
                count++
                index++
            }
        }

        println(count)
    }
}
