package `2024`.`08`

import aoc.utils.pr
import java.io.File

private const val FILENAME = "2024/08/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }

    val antinodes = HashSet<Pair<Int, Int>>()

    val maxY = file.size
    val maxX = file[0].size

    for (y1 in file.indices) {
        for (x1 in file[y1].indices) {
            if (file[y1][x1] == '.') {
                continue
            }

            for (y2 in file.indices) {
                for (x2 in file[y2].indices) {
                    if (y1 == y2 && x1 == x2) {
                        continue
                    }

                    if (file[y1][x1] == file[y2][x2]) {
                        val dy = y1 - y2
                        val dx = x1 - x2

                        var new = Pair(y1 + dy, x1 + dx)

                        if (new.first >= 0 && new.first < maxY && new.second >= 0 && new.second < maxX) {
                            antinodes.add(new)
                        }
                    }
                }
            }
        }
    }

    pr(antinodes.size)
}

private fun partTwo() {
    val file = File(FILENAME).readLines().map { it.toCharArray() }

    val antinodes = HashSet<Pair<Int, Int>>()
    val maxY = file.size
    val maxX = file[0].size

    for (y1 in file.indices) {
        for (x1 in file[y1].indices) {
            if (file[y1][x1] == '.') {
                continue
            }

            antinodes.add(Pair(y1, x1))

            for (y2 in file.indices) {
                for (x2 in file[y2].indices) {
                    if (y1 == y2 && x1 == x2) {
                        continue
                    }

                    if (file[y1][x1] == file[y2][x2]) {
                        val dy = y1 - y2
                        val dx = x1 - x2

                        var new = Pair(y1 + dy, x1 + dx)

                        while (new.first >= 0 && new.first < maxY && new.second >= 0 && new.second < maxX) {
                            antinodes.add(new)
                            new = Pair(new.first + dy, new.second + dx)
                        }
                    }
                }
            }
        }
    }

    pr(antinodes.size)
}
