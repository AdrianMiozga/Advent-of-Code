import java.io.File
import java.util.*

private const val FILENAME = "2023/18/input.txt"
private const val GROUND = '.'
private const val TRENCH = '#'

private data class Position(var x: Int, var y: Int)

fun main() {
    partOne()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    val array: MutableList<MutableList<Char>> =
        mutableListOf(mutableListOf(GROUND))

    val position = Position(0, 0)

    for (line in file) {
        val (direction, length) = Regex("""(\w) (\d+) \(#\w+\)""").matchEntire(
            line
        )!!.destructured

        for (i in 0 until length.toInt()) {
            when (direction) {
                "R" -> position.x++
                "L" -> position.x--
                "U" -> position.y--
                "D" -> position.y++
            }

            // Grow right
            if (position.x >= array[0].size) {
                for (row in array) {
                    row.addLast(GROUND)
                }
            }

            // Grow left
            if (position.x < 0) {
                for (row in array) {
                    row.addFirst(GROUND)
                }

                position.x = 0
            }

            // Grow down
            if (position.y >= array.size) {
                array.addLast(MutableList(array[0].size) { GROUND })
            }

            // Grow up
            if (position.y < 0) {
                array.addFirst(MutableList(array[0].size) { GROUND })
                position.y = 0
            }

            array[position.y][position.x] = TRENCH
        }
    }

//    floodFill(array, Pair(1, 1))
    floodFill(array, Pair(151, 1))

    val count = array.sumOf { it.count { char -> char == TRENCH } }
    println(count)
}

private fun floodFill(
    array: MutableList<MutableList<Char>>, initialPosition: Pair<Int, Int>
) {
    val stack = Stack<Pair<Int, Int>>()
    stack.push(Pair(initialPosition.first, initialPosition.second))

    while (stack.isNotEmpty()) {
        val current = stack.pop()
        val x = current.first
        val y = current.second

        if (x < 0 || y < 0) {
            continue
        }

        if (y >= array.size || x >= array[0].size) {
            continue
        }

        if (array[y][x] == TRENCH) {
            continue
        }

        array[y][x] = TRENCH

        stack.push(Pair(x + 1, y))
        stack.push(Pair(x - 1, y))
        stack.push(Pair(x, y + 1))
        stack.push(Pair(x, y - 1))
    }
}
