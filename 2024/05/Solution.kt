import java.io.File
import kotlin.collections.mutableMapOf

private const val FILENAME = "2024/05/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val (rules, pagesToProduce) = File(FILENAME).readText().split("\r\n\r\n").map { it.split("\r\n") }

    val rulesMap = mutableMapOf<Int, MutableList<Int>>()

    for (rule in rules) {
        val (x, y) = rule.split("|").map { it.toInt() }

        if (rulesMap[x] == null) {
            rulesMap[x] = mutableListOf(y)
        } else {
            rulesMap[x]!!.add(y)
        }
    }

    val goodPagesToProduce = mutableListOf<Int>()

    nextPage@for ((pageIndex, pageToProduce) in pagesToProduce.withIndex()) {
        val array = pageToProduce.split(",").map { it.toInt() }

        for ((index, element) in array.withIndex()) {
            for (i in 0 until index) {
                if (rulesMap[element]?.contains(array[i]) == true) {
                    continue@nextPage
                }
            }
        }

        goodPagesToProduce.add(pageIndex)
    }

    var result = 0

    for (element in goodPagesToProduce) {
        val array = pagesToProduce[element].split(",").map { it.toInt() }
        result += (array[array.size / 2])
    }

    println(result)
}

private fun partTwo() {
    val (rules, pagesToProduce) = File(FILENAME).readText().split("\r\n\r\n").map { it.split("\r\n") }

    val rulesMap = mutableMapOf<Int, MutableList<Int>>()

    for (rule in rules) {
        val (x, y) = rule.split("|").map { it.toInt() }

        if (rulesMap[x] == null) {
            rulesMap[x] = mutableListOf(y)
        } else {
            rulesMap[x]!!.add(y)
        }
    }

    val toSort = mutableListOf<Int>()

    nextPage@for ((pageIndex, pageToProduce) in pagesToProduce.withIndex()) {
        val array = pageToProduce.split(",").map { it.toInt() }

        for ((index, element) in array.withIndex()) {
            for (i in 0 until index) {
                if (rulesMap[element]?.contains(array[i]) == true) {
                    toSort.add(pageIndex)
                    continue@nextPage
                }
            }
        }
    }

    var result = 0

    for (element in toSort) {
        val sort = pagesToProduce[element].split(",").map { it.toInt() }.toMutableList()

        for (i in 0 until sort.size) {
            for (y in 1 until sort.size - i) {
                if (rulesMap[sort[y]]?.contains(sort[y - 1]) == true) {
                    val temp = sort[y - 1]
                    sort[y - 1] = sort[y]
                    sort[y] = temp
                }
            }
        }

        result += sort[sort.size / 2]
    }

    println(result)
}
