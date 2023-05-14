import java.io.File

private const val FILENAME = "2020/03/03.txt"
private const val TREE = '#'

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val lines = File(FILENAME).readLines()

    var treeCount = 0
    var currentX = 0
    val width = lines[0].length

    for (currentY in 1 until lines.size) {
        currentX = (currentX + 3) % width

        if (lines[currentY][currentX] == TREE) {
            treeCount++
        }
    }

    println(treeCount)
}

private fun partTwo() {
    val lines = File(FILENAME).readLines()

    var treeCount = 1L
    val width = lines[0].length
    val vectors = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)

    for (vector in vectors) {
        var currentX = 0
        var currentTrees = 0

        for (currentY in vector.second until lines.size step vector.second) {
            currentX = (currentX + vector.first) % width

            if (lines[currentY][currentX] == TREE) {
                currentTrees++
            }
        }

        treeCount *= currentTrees
    }

    println(treeCount)
}
