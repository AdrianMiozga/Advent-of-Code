import java.io.File

private const val FILENAME = "2023/06/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val times =
        file[0].slice(9 until file[0].length).trim().split(Regex("""\s+"""))
            .map { it.toInt() }
    val distance =
        file[1].slice(9 until file[0].length).trim().split(Regex("""\s+"""))
            .map { it.toInt() }

    var result = 1

    for (i in times.indices) {
        var counter = 0

        for (j in 1 until times[i]) {
            val calculatedDistance = j * (times[i] - j)

            if (calculatedDistance > distance[i]) {
                counter++
            }
        }

        result *= counter
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val times = file[0].slice(9 until file[0].length).trim()
        .replace(Regex("""\s+"""), "").toLong()
    val distance = file[1].slice(9 until file[0].length).trim()
        .replace(Regex("""\s+"""), "").toLong()

    var result = 0

    for (i in 1 until times) {
        val calculatedDistance = i * (times - i)

        if (calculatedDistance > distance) {
            result++
        }
    }

    println(result)
}
