import java.io.File

private const val FILENAME = "2023/10/input.txt"

val directionMapping = mapOf(
    0 to Pair(-1, 0), 1 to Pair(0, 1), 2 to Pair(1, 0), 3 to Pair(0, -1)
)

fun main() {
    partOne()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }

    var position = Pair(0, 0)

    for ((row, line) in file.withIndex()) {
        for ((column, character) in line.withIndex()) {
            if (character == 'S') {
                position = Pair(row, column)
            }
        }
    }

    var direction = 0
    var steps = 0

    if (file[position.first][position.second + 1] in listOf('-', '7', 'J')) {
        direction = 1
    } else if (file[position.first + 1][position.second] in listOf(
            '|', 'L', 'J'
        )
    ) {
        direction = 2
    } else if (file[position.first][position.second - 1] in listOf(
            '-', 'F', 'L'
        )
    ) {
        direction = 3
    } else if (file[position.first - 1][position.second] in listOf(
            '|', 'F', '7'
        )
    ) {
        direction = 0
    }

    position = Pair(
        position.first + directionMapping[direction]!!.first,
        position.second + directionMapping[direction]!!.second
    )

    while (true) {
        if (file[position.first][position.second] == 'S') {
            break
        }

        when (file[position.first][position.second]) {
            'L' -> {
                if (direction == 3) {
                    direction = 0
                } else if (direction == 2) {
                    direction = 1
                }
            }

            'J' -> {
                if (direction == 2) {
                    direction = 3
                } else if (direction == 1) {
                    direction = 0
                }
            }

            '7' -> {
                if (direction == 0) {
                    direction = 3
                } else if (direction == 1) {
                    direction = 2
                }
            }

            'F' -> {
                if (direction == 0) {
                    direction = 1
                } else if (direction == 3) {
                    direction = 2
                }
            }
        }

        file[position.first][position.second] = 'X'

        position = Pair(
            position.first + directionMapping[direction]!!.first,
            position.second + directionMapping[direction]!!.second
        )

        steps++
    }

    println((steps + 1) / 2)
}
