import java.io.File

private const val FILENAME = "2023/15/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it.split(",") }.first()

    var result = 0

    for (element in file) {
        result += hash(element)
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines().map { it.split(",") }.first()

    val boxes = HashMap<Int, MutableList<Pair<String, Int>>>()

    for (label in file) {
        if (label.contains("-")) {
            val lens = label.replace("-", "")

            val currentValue = hash(lens)

            if (boxes[currentValue] == null) {
                continue
            }

            var removeIndex = -1

            for ((index, pair) in boxes[currentValue]!!.withIndex()) {
                if (pair.first == lens) {
                    removeIndex = index
                }
            }

            if (removeIndex != -1) {
                boxes[currentValue]!!.removeAt(removeIndex)
            }
        } else {
            val (lens, value) = Regex("""(\w+)=(\d+)""").matchEntire(label)!!.destructured

            val currentValue = hash(lens)

            val list = boxes[currentValue]

            if (list == null) {
                boxes[currentValue] = mutableListOf(Pair(lens, value.toInt()))
            } else {
                var removeIndex = -1

                for ((index, pair) in boxes[currentValue]!!.withIndex()) {
                    if (pair.first == lens) {
                        removeIndex = index
                    }
                }

                if (removeIndex != -1) {
                    boxes[currentValue]!!.removeAt(removeIndex)
                    boxes[currentValue]!!.add(removeIndex, Pair(lens, value.toInt()))
                } else {
                    boxes[currentValue]!!.add(Pair(lens, value.toInt()))
                }
            }
        }
    }

    var result = 0

    for (box in boxes) {
        for ((slot, values) in box.value.withIndex()) {
            var temp = box.key + 1
            temp *= slot + 1
            temp *= values.second
            result += temp
        }
    }

    println(result)
}

private fun hash(lens: String): Int {
    var currentValue = 0

    for (character in lens) {
        currentValue += character.code
        currentValue *= 17
        currentValue %= 256
    }

    return currentValue
}
