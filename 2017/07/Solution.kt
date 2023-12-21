import java.io.File

private const val FILENAME = "2017/07/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val list = File(FILENAME).readLines()

    var min = Int.MAX_VALUE
    var minValue = ""

    for (element in list) {
        val (value, number) = Regex("""(\w+) \((\d+)\)""").find(element)!!.destructured

        if (min > number.toInt()) {
            min = number.toInt()
            minValue = value
        }
    }

    next@ while (true) {
        for (element in list) {
            val after = element.substringAfter("->")

            if (after != element) {
                if (after.contains(minValue)) {
                    minValue = element.substringBefore(" ")
                    continue@next
                }
            }
        }

        break
    }

    println(minValue)
}
