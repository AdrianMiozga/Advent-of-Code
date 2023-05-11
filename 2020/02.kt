import java.io.File

private const val FILENAME = "2020/02.txt"
private val REGEX = Regex("""(\d+)-(\d+)\s(\w):\s(\w+)""")

// No input validation done as it is always correct

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val lines = File(FILENAME).readLines()

    var correctPasswords = 0

    lines.forEach { line ->
        val (min, max, letter, password) = REGEX.matchEntire(line)!!.destructured
        val range = min.toInt()..max.toInt()
        val count = password.count { it == letter.single() }

        if (count in range) {
            correctPasswords++
        }
    }

    println(correctPasswords)
}

private fun partTwo() {
    val lines = File(FILENAME).readLines()

    var correctPasswords = 0

    lines.forEach { line ->
        val (firstPosition, secondPosition, letter, password) = REGEX.matchEntire(line)!!.destructured

        // Subtract one from indexes as input file starts indexing at 1, but string indexes from 0
        val first: Boolean = password[firstPosition.toInt() - 1] == letter.single()
        val second: Boolean = password[secondPosition.toInt() - 1] == letter.single()

        if (first xor second) {
            correctPasswords++
        }
    }

    println(correctPasswords)
}
