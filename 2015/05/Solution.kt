import java.io.File

private const val FILENAME = "2015/05/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    var niceCount = 0

    val lines = File(FILENAME).readLines()

    for (line in lines) {
        var vowelCount = 0
        var doubleLetters = false
        var clean = false

        for (char in line) {
            when (char) {
                'a', 'e', 'i', 'o', 'u' -> vowelCount += 1
            }
        }

        var previousLetter = line[0]
        for (i in 1 until line.length) {
            if (line[i] == previousLetter) {
                doubleLetters = true
            }

            previousLetter = line[i]
        }

        if ("ab" !in line && "cd" !in line && "pq" !in line && "xy" !in line) {
            clean = true
        }

        if (vowelCount >= 3 && doubleLetters && clean) {
            niceCount++
        }
    }

    println(niceCount)
}
