package `2015`.`11`

import java.io.File
import kotlin.text.iterator

private const val FILENAME = "2015/11/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    calculate(File(FILENAME).readText())
}

private fun partTwo() {
    calculate("hxbxxyzz")
}

private fun incrementString(string: String): String {
    var result = ""
    var add = true

    for (letter in string.reversed()) {
        if (add) {
            if (letter.code + 1 > 122) {
                result += 'a'
                add = true
            } else {
                result += (letter.code + 1).toChar()
                add = false
            }
        } else {
            result += letter
        }
    }

    if (add) {
        result += 'a'
    }

    return result.reversed()
}

private fun calculate(password: String) {
    var next = password

    while (true) {
        next = incrementString(next)

        var firstRule = true

        if (next.contains(Regex("""[iol]"""))) {
            firstRule = false
        }

        var secondRule = false

        for (index in 0 until next.length - 2) {
            if (next[index + 1].code == next[index].code + 1 && next[index + 2].code == next[index].code + 2) {
                secondRule = true
                break
            }
        }

        var thirdRule = false
        val set = HashSet<String>()

        var index = 0

        while (index < next.length - 1) {
            if (next[index] == next[index + 1]) {
                set.add("${next[index]}${next[index + 1]}")
            }

            if (index < next.length - 2 && next[index + 2] == next[index]) {
                index += 2
            } else {
                index++
            }
        }

        if (set.size > 1) {
            thirdRule = true
        }

        if (firstRule && secondRule && thirdRule) {
            println(next)
            break
        }
    }
}
