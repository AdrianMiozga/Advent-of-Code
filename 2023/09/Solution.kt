import java.io.File

private const val FILENAME = "2023/09/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
        .map { it -> it.split(" ").map { it.toInt() } }

    var sum = 0

    for (line in file) {
        val sequence: MutableList<MutableList<Int>> = mutableListOf()
        sequence.add(line.toMutableList())

        while (true) {
            val inner = mutableListOf<Int>()

            for (i in 0 until sequence.last().size - 1) {
                inner.add(sequence.last()[i + 1] - sequence.last()[i])
            }

            sequence.add(inner)

            if (inner.all { it == 0 }) {
                break
            }
        }

        for (i in sequence.size - 2 downTo 0) {
            val newValue = sequence[i].last().plus(sequence[i + 1].last())
            sequence[i].add(newValue)
        }

        sum += sequence.first().last()
    }

    println(sum)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()
        .map { it -> it.split(" ").map { it.toInt() } }

    var sum = 0

    for (line in file) {
        val sequence: MutableList<MutableList<Int>> = mutableListOf()
        sequence.add(line.toMutableList())

        while (true) {
            val inner = mutableListOf<Int>()

            for (i in 0 until sequence.last().size - 1) {
                inner.add(sequence.last()[i + 1] - sequence.last()[i])
            }

            sequence.add(inner)

            if (inner.all { it == 0 }) {
                break
            }
        }

        for (i in sequence.size - 2 downTo 0) {
            val newValue = sequence[i].first().minus(sequence[i + 1].first())
            sequence[i].add(0, newValue)
        }

        sum += sequence.first().first()
    }

    println(sum)
}
