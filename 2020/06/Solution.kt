import java.io.File

private const val FILENAME = "2020/06/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val result = File(FILENAME)
        .readText()
        .split("\r\n\r\n")
        .map {
            it.replace("\r\n", " ")
        }.map {
            it.replace(" ", "")
        }.map {
            it.toSet()
        }.sumOf {
            it.size
        }

    println(result)
}
