import java.io.File

private const val FILENAME = "2023/14/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().toMutableList()

    moveUp(file)

    println(calculateLoad(file))
}

private fun calculateLoad(file: MutableList<String>): Int {
    var result = 0

    for ((index, line) in file.withIndex()) {
        val count = line.count { it == 'O' }
        result += (file.size - index) * count
    }

    return result
}

private fun moveUp(file: MutableList<String>) {
    repeat(file.size - 1) {
        for (row in 1 until file.size) {
            for ((column, character) in file[row].withIndex()) {
                if (character != 'O') {
                    continue
                }

                if (file[row - 1][column] == '.') {
                    file[row] = file[row].replaceRange(column, column + 1, ".")
                    file[row - 1] =
                        file[row - 1].replaceRange(column, column + 1, "O")
                }
            }
        }
    }
}

private fun partTwo() {
    var file = File(FILENAME).readLines().toMutableList()
    val cycles = 1_000_000_000

    val values = HashMap<Int, Int>()
    val listValues = mutableListOf<Int>()

    for (cycle in 0 until cycles) {
        // North
        moveUp(file)

        // West
        file = transpose(file)
        moveUp(file)
        file = transpose(file)

        // South
        file = file.reversed().toMutableList()
        moveUp(file)
        file = file.reversed().toMutableList()

        // East
        file = transpose(file)
        file = file.reversed().toMutableList()
        moveUp(file)
        file = file.reversed().toMutableList()
        file = transpose(file)

        val load = calculateLoad(file)

        listValues.add(load)

        if (values.contains(load)) {
            values[load] = (values[load]!! + 1)
        } else {
            values[load] = 1
        }

        // Wait for numbers to stabilize
        if (cycle == 250) {
            break
        }
    }

    // Find index where the sequence begins
    var smallest = Integer.MAX_VALUE

    for (key in values.filterValues { it > 2 }.keys) {
        for ((index, element) in listValues.withIndex()) {
            if (element == key) {
                if (index < smallest) {
                    smallest = index
                }
            }
        }
    }

    val sequenceLength = values.filterValues { it > 2 }.size
    println(listValues[smallest + ((cycles - smallest - 1) % sequenceLength)])
}

private fun transpose(list: List<String>): MutableList<String> {
    val result = mutableListOf<String>()

    for (column in list[0].indices) {
        var columnNew = ""

        for (row in list.indices) {
            columnNew += list[row][column]
        }

        result.add(columnNew)
    }

    return result
}
