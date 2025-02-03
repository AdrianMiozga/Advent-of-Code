package `2024`.`15`

import aoc.utils.Position
import java.io.File

private const val FILENAME = "2024/15/input.txt"

fun main() {
    partOne()
}

private const val WALL = '#'
private const val ROBOT = '@'
private const val EMPTY = '.'
private const val BOX = 'O'

private fun partOne() {
    val (gridText, instructions) = File(FILENAME).readText().split("\r\n\r\n")

    val grid = gridText.split("\r\n").map { it.toCharArray() }.toMutableList()

    var robotPosition = getRobotPosition(grid)

    for (instruction in instructions) {
        val direction = when (instruction) {
            '^' -> Direction.UP
            'v' -> Direction.DOWN
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> continue
        }

        val nextPosition = Position(robotPosition.y + direction.y, robotPosition.x + direction.x)
        val nextTile = grid[nextPosition.y][nextPosition.x]

        when (nextTile) {
            EMPTY -> {
                grid[robotPosition.y][robotPosition.x] = EMPTY
                grid[nextPosition.y][nextPosition.x] = ROBOT
                robotPosition = Position(nextPosition.y, nextPosition.x)
            }

            BOX -> {
                var steps = 1

                var checkPosition = Position(nextPosition.y, nextPosition.x)

                while (grid[checkPosition.y][checkPosition.x] != WALL) {
                    steps++
                    checkPosition = Position(checkPosition.y + direction.y, checkPosition.x + direction.x)

                    if (grid[checkPosition.y][checkPosition.x] == EMPTY) {
                        repeat(steps) {
                            val previousTile = grid[checkPosition.y - direction.y][checkPosition.x - direction.x]

                            if (previousTile == ROBOT) {
                                robotPosition = Position(checkPosition.y, checkPosition.x)
                            }

                            val temp = grid[checkPosition.y][checkPosition.x]

                            grid[checkPosition.y][checkPosition.x] =
                                previousTile

                            grid[checkPosition.y - direction.y][checkPosition.x - direction.x] = temp

                            checkPosition = Position(checkPosition.y - direction.y, checkPosition.x - direction.x)
                        }

                        break
                    }
                }
            }
        }
    }

    var result = 0

    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (grid[y][x] == 'O') {
                result += 100 * y + x
            }
        }
    }

    println(result)
}

private fun getRobotPosition(grid: MutableList<CharArray>): Position {
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (grid[y][x] == ROBOT) {
                return Position(y, x)
            }
        }
    }

    return Position(0, 0)
}

enum class Direction(val y: Int, val x: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1)
}
