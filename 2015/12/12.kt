import java.io.File

private const val FILENAME = "2015/12/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val file = File(FILENAME).readText()

    val result = file.split(Regex("""[^0-9\-]""")).filter { it.isNotBlank() }.sumOf { it.toInt() }

    println(result)
}
