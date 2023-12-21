import java.io.File

private const val FILENAME = "2022/03/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    var sum = 0

    for (line in file) {
        val half = line.length / 2

        val first = line.slice(0 until half)
        val second = line.slice(half until line.length)

        val firstMap = first.groupingBy { it }.eachCount()
        val secondMap = second.groupingBy { it }.eachCount()

        for (i in firstMap) {
            if (secondMap[i.key] != null) {
                sum += if (i.key in 'a'..'z') {
                    i.key.code - 96
                } else {
                    i.key.code - 38
                }
            }
        }
    }

    println(sum)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()
    var sum = 0

    for (index in file.indices step 3) {
        for (element in file[index]) {
            if (file[index + 1].contains(element) && file[index + 2].contains(element)) {
                sum += if (element in 'a'..'z') {
                    element.code - 96
                } else {
                    element.code - 38
                }

                break
            }
        }
    }

    println(sum)
}
