import java.io.File
import kotlin.math.pow

private const val FILENAME = "2023/12/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    var result = 0

    for (line in file) {
        val (first, second) = line.split(" ")
        val length = first.length

        val pattern =
            first.replace(".", "0").replace("#", "1").replace("?", ".")

        for (i in 0 until (2.0.pow(length).toInt())) {
            val representation = Integer.toBinaryString(i).padStart(length, '0')

            if (!representation.matches(Regex(pattern))) {
                continue
            }

            val groups = representation.split("0").filter { it.isNotEmpty() }
                .map { it.length }.joinToString(separator = ",")

            if (groups == second) {
                result++
            }
        }
    }

    println(result)
}
