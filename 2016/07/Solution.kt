import java.io.File

private const val FILENAME = "2016/07/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    var valid = 0

    for (ip in file) {
        val inBrackets =
            Regex("""\[\w+]""")
                .findAll(ip)
                .flatMap { it.groupValues }
                .map { it.removeSurrounding("[", "]") }
                .toList()

        val outsideBrackets =
            Regex("""\w+""")
                .findAll(ip)
                .flatMap { it.groupValues }.toList()
                .minus(inBrackets.toSet())

        val outsideRule = containsAbba(outsideBrackets)
        val insideRule = !containsAbba(inBrackets)

        if (outsideRule && insideRule) {
            valid++
        }
    }

    println(valid)
}

private fun containsAbba(strings: List<String>): Boolean {
    for (string in strings) {
        for (i in 0 until (string.length - 3)) {
            if (string[i] == string[i + 1]) {
                continue
            }

            val firstPair = "${string[i]}${string[i + 1]}"
            val secondPair = "${string[i + 2]}${string[i + 3]}"

            if (firstPair == secondPair.reversed()) {
                return true
            }
        }
    }

    return false
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    var valid = 0

    ip@for (ip in file) {
        val inBrackets =
            Regex("""\[\w+]""")
                .findAll(ip)
                .flatMap { it.groupValues }
                .map { it.removeSurrounding("[", "]") }
                .toList()

        val outsideBrackets =
            Regex("""\w+""")
                .findAll(ip)
                .flatMap { it.groupValues }.toList()
                .minus(inBrackets.toSet())

        val sequences = HashSet<String>()

        for (element in outsideBrackets) {
            for (index in 0 until element.length - 2) {
                if (element[index] == element[index + 2]) {
                    if (element[index + 1] != element[index]) {
                        sequences.add(element.substring(index..(index + 2)))
                    }
                }
            }
        }

        for (element in inBrackets) {
            for (index in 0 until element.length - 2) {
                for (sequence in sequences) {
                    if (element[index] == sequence[1] && element[index + 2] == sequence[1]) {
                        if (element[index + 1] == sequence[0]) {
                            valid++
                            continue@ip
                        }
                    }
                }
            }
        }
    }

    println(valid)
}
