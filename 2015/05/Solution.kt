import java.io.File

private const val FILENAME = "2015/05/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    var niceCount = 0

    val strings = File(FILENAME).readLines()

    for (string in strings) {
        var vowelCount = 0
        var doubleLetters = false
        var clean = false

        for (char in string) {
            when (char) {
                'a', 'e', 'i', 'o', 'u' -> vowelCount += 1
            }
        }

        var previousLetter = string[0]
        for (i in 1 until string.length) {
            if (string[i] == previousLetter) {
                doubleLetters = true
            }

            previousLetter = string[i]
        }

        if ("ab" !in string && "cd" !in string && "pq" !in string && "xy" !in string) {
            clean = true
        }

        if (vowelCount >= 3 && doubleLetters && clean) {
            niceCount++
        }
    }

    println(niceCount)
}

private fun partTwo() {
    val strings = File(FILENAME).readLines()

    var niceCount = 0

    for (string in strings) {
        var hasPair = false
        var hasLetterBetween = false

        val pairs = HashSet<String>()

        var index = 0

        while (index < string.length - 1) {
            val pair = "${string[index]}${string[index + 1]}"

            if (pairs.contains(pair)) {
                hasPair = true
                break
            } else {
                pairs.add(pair)
            }

            if (index < string.length - 2) {
                if (string[index] == string[index + 1] && string[index + 2] == string[index]) {
                    index += 2
                    continue
                }
            }

            index++
        }

        for (character in 0 until string.length - 2) {
            if (string[character] == string[character + 2]) {
                hasLetterBetween = true
                break
            }
        }

        if (hasPair && hasLetterBetween) {
            niceCount++
        }
    }

    println(niceCount)
}
