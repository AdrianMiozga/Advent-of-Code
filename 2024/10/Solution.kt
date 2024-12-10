package `2024`.`10`

import aoc.utils.pr
import java.io.File

private const val FILENAME = "2024/10/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val grid = File(FILENAME).readLines()
        .map { string -> string.map { char -> char.digitToInt() }.toList() }

    val startingPositions = mutableListOf<Pair<Int, Int>>()
    val connections =
        mutableMapOf<Pair<Int, Int>, MutableList<Pair<Int, Int>>>()

    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (grid[y][x] == 0) {
                startingPositions.add(Pair(y, x))
                connections[Pair(y, x)] = mutableListOf<Pair<Int, Int>>()
            }
        }
    }

    var result = 0

    for (startingPosition in startingPositions) {
        result += solvePartOne(
            grid,
            mutableSetOf(startingPosition),
            connections,
            startingPosition,
        )
    }

    pr(result)
}

private fun solvePartOne(
    grid: List<List<Int>>,
    path: MutableSet<Pair<Int, Int>>,
    connections: Map<Pair<Int, Int>, MutableList<Pair<Int, Int>>>,
    currentPosition: Pair<Int, Int>,
): Int {
    if (grid[currentPosition.first][currentPosition.second] == 9) {
        val startingPosition = path.first()

        if (connections[startingPosition]!!.contains(currentPosition)) {
            return 0
        } else {
            connections[startingPosition]!!.add(currentPosition)
            return 1
        }
    }

    val maxY = grid.size
    val maxX = grid.first().size
    var result = 0

    val directions = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1),
    )

    for (direction in directions) {
        val nextPosition = Pair(
            currentPosition.first + direction.first,
            currentPosition.second + direction.second,
        )

        if (nextPosition.first !in 0 until maxY || nextPosition.second !in 0 until maxX) {
            continue
        }

        if (path.contains(nextPosition)) {
            continue
        }

        val currentHeight = grid[currentPosition.first][currentPosition.second]
        val nextHeight = grid[nextPosition.first][nextPosition.second]

        if (nextHeight != currentHeight + 1) {
            continue
        }

        path.add(nextPosition)
        result += solvePartOne(grid, path, connections, nextPosition)
        path.remove(nextPosition)
    }

    return result
}

private fun partTwo() {
    val grid = File(FILENAME).readLines()
        .map { it.map { it.toString().toInt() }.toList() }

    val startingPositions = mutableListOf<Pair<Int, Int>>()

    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (grid[y][x] == 0) {
                startingPositions.add(Pair(y, x))
            }
        }
    }

    var result = 0

    for (startingPosition in startingPositions) {
        result += solvePartTwo(
            grid,
            mutableSetOf(startingPosition),
            startingPosition,
        )
    }

    pr(result)
}

private fun solvePartTwo(
    grid: List<List<Int>>,
    path: MutableSet<Pair<Int, Int>>,
    currentPosition: Pair<Int, Int>,
): Int {
    if (grid[currentPosition.first][currentPosition.second] == 9) {
        return 1
    }

    val maxY = grid.size
    val maxX = grid.first().size

    var result = 0

    val directions = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1),
    )

    for (direction in directions) {
        val nextPosition = Pair(
            currentPosition.first + direction.first,
            currentPosition.second + direction.second,
        )

        if (nextPosition.first !in 0 until maxY || nextPosition.second !in 0 until maxX) {
            continue
        }

        if (path.contains(nextPosition)) {
            continue
        }

        val currentHeight = grid[currentPosition.first][currentPosition.second]
        val nextHeight = grid[nextPosition.first][nextPosition.second]

        if (nextHeight != currentHeight + 1) {
            continue
        }

        path.add(nextPosition)
        result += solvePartTwo(grid, path, nextPosition)
        path.remove(nextPosition)
    }

    return result
}
