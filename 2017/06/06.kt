import java.io.File

private const val FILENAME = "2017/06/input.txt"
private const val REGEX = """\s+"""

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val banks = File(FILENAME).readText().split(Regex(REGEX)).map { it.toInt() }
        .toMutableList()

    val seen = HashSet<List<Int>>()

    var counter = 1

    while (true) {
        var max = Int.MIN_VALUE
        var maxIndex = -1

        for ((index, bank) in banks.withIndex()) {
            if (bank > max) {
                max = bank
                maxIndex = index
            }
        }

        banks[maxIndex] = 0

        var divider = max / (banks.size - 1)

        val remainder = if (divider > 0) {
            max % (banks.size - 1)
        } else {
            0
        }

        if (divider == 0) {
            divider = 1
        }

        for (i in 0 until (max / divider)) {
            val index = (maxIndex + i + 1) % banks.size

            banks[index] += divider
        }

        if (remainder != 0) {
            banks[maxIndex] = remainder
        }

        if (seen.contains(banks)) {
            break
        }

        seen.add(banks)
        counter++
    }

    println(counter)
}

private fun partTwo() {
    val banks = File(FILENAME).readText().split(Regex(REGEX)).map { it.toInt() }
        .toMutableList()

    val seen = HashMap<List<Int>, Int>()

    var counter = 1

    while (true) {
        var max = Int.MIN_VALUE
        var maxIndex = -1

        for ((index, bank) in banks.withIndex()) {
            if (bank > max) {
                max = bank
                maxIndex = index
            }
        }

        banks[maxIndex] = 0

        var divider = max / (banks.size - 1)

        val remainder = if (divider > 0) {
            max % (banks.size - 1)
        } else {
            0
        }

        if (divider == 0) {
            divider = 1
        }

        for (i in 0 until (max / divider)) {
            val index = (maxIndex + i + 1) % banks.size

            banks[index] += divider
        }

        if (remainder != 0) {
            banks[maxIndex] = remainder
        }

        if (seen.contains(banks)) {
            println(counter - seen[banks]!!)
            break
        }

        seen[banks] = counter
        counter++
    }
}
