import java.io.File

private const val FILENAME = "2020/10/example-2.txt"

fun main() {
//    partOne()
    partTwo()
}

private fun partOne() {
    val lines = File(FILENAME).readLines().map { it.toInt() }.sorted()

    val adapterRating = lines.last() + 3

    var jolts = 0
    val differences: IntArray = intArrayOf(0, 0, 0)

    for (number in lines) {
        val difference = number - jolts

        when (difference) {
            1, 2, 3 -> {
                differences[difference - 1]++
            }

            else -> {
                println("ERROR: Difference bigger than 3")
                return
            }
        }

        jolts += difference
    }

    if (jolts <= adapterRating + 3) {
        differences[2]++
    }

    println(differences[0] * differences[2])
}

private fun partTwo() {
    val lines = File(FILENAME).readLines().map { it.toInt() }.sorted()

    println(lines)

    var index = 0
    var combinations = 1

    do {
        if (index >= lines.size - 1) {
            break
        }

        var step = 0

        for (i in 1..3) {
            if (lines[index + 1] == lines[index] + i) {
                step++
            }

            if (index + 2 < lines.size) {
                if (lines[index + 2] == lines[index] + i) {
                    step++
                }
            }

            if (index + 3 < lines.size) {
                if (lines[index + 3] == lines[index] + i) {
                    step++
                }
            }
        }

        println("$index - $step")

        index += step

        if (step > 1) {
            combinations += (step + 1)
        }
    } while (true)

    println(combinations)
}
