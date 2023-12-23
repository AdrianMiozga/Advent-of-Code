import java.io.File

private const val FILENAME = "2016/07/input.txt"

fun main() {
    partOne()
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

private fun containsAbba(outsideBrackets: List<String>): Boolean {
    for (element in outsideBrackets) {
        for (i in 0 until (element.length - 3)) {
            if (element[i] == element[i + 1]) {
                continue
            }

            val firstPair = "${element[i]}${element[i + 1]}"
            val secondPair = "${element[i + 2]}${element[i + 3]}"

            if (firstPair == secondPair.reversed()) {
                return true
            }
        }
    }

    return false
}
