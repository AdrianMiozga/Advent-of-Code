import java.io.File

private const val FILENAME = "2020/09/input.txt"
private const val PREAMBLE = 25

fun main() {
    val result = partOne()
    partTwo(result)
}

private fun partOne(): Long {
    var result = 0L

    val lines = File(FILENAME).readLines()

    entry@ for (i in PREAMBLE + 1 until lines.size) {
        val numbers: MutableSet<Long> = mutableSetOf()

        for (j in i - PREAMBLE until i) {
            for (k in i - PREAMBLE until i) {
                if (j == k) {
                    continue
                }

                val sum = lines[j].toLong() + lines[k].toLong()
                if (sum == lines[i].toLong()) {
                    continue@entry
                }
                numbers.add(sum)
            }
        }

        result = lines[i].toLong()
        println(result)
    }

    return result
}

private fun partTwo(input: Long) {
    val lines = File(FILENAME).readLines()

    var min = 0
    var max = 1

    do {
        var sum = 0L
        val numbers: MutableSet<Long> = mutableSetOf()

        for (i in min..max) {
            sum += lines[i].toLong()
            numbers.add(lines[i].toLong())
        }

        if (sum == input) {
            val sorted = numbers.sorted()
            println(sorted.first() + sorted.last())
            return
        } else if (sum > input) {
            min += 1
            max = min + 1
        } else {
            max++
        }
    } while (true)
}
