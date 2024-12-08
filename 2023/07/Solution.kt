package `2023`.`07`

import java.io.File
import kotlin.text.iterator

private const val FILENAME = "2023/07/input.txt"

private val numericToRank = mapOf(
    "5" to 6,
    "41" to 5,
    "32" to 4,
    "311" to 3,
    "221" to 2,
    "2111" to 1,
)

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val hands: MutableList<HandOne> = mutableListOf()

    for (line in file) {
        val (type, bid) = line.split(" ")

        val numeric = type.groupingBy { it }.eachCount().values.toList()
            .sortedDescending().joinToString(separator = "")

        val rank = numericToRank[numeric] ?: 0
        hands.add(HandOne(type, bid, rank))
    }

    var result = 0

    for ((index, hand) in hands.sorted().withIndex()) {
        result += (hand.bid.toInt() * (index + 1))
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val hands: MutableList<HandTwo> = mutableListOf()

    for (line in file) {
        val (type, bid) = line.split(" ")

        val map = HashMap<Char, Int>()

        var jCount = 0
        for (character in type) {
            if (character == 'J') {
                jCount++
                continue
            }

            if (map[character] != null) {
                map[character] = map[character]!! + 1
            } else {
                map[character] = 1
            }
        }

        val numeric =
            map.values.toList().sortedDescending().joinToString(separator = "")

        val updated = if (numeric.isEmpty()) {
            "5"
        } else {
            (numeric[0].code - 48 + jCount).toString() + numeric.slice(1 until numeric.length)
        }

        val rank = numericToRank[updated] ?: 0
        hands.add(HandTwo(type, bid, rank))
    }

    var result = 0

    for ((index, hand) in hands.sorted().withIndex()) {
        result += (hand.bid.toInt() * (index + 1))
    }

    println(result)
}
