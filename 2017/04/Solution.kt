package `2017`.`04`

import java.io.File

private const val FILENAME = "2017/04/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val passwords = File(FILENAME).readLines()

    var count = 0

    password@for (password in passwords) {
        val words = HashSet<String>()

        for (word in password.split(" ")) {
            if (word in words) {
                continue@password
            } else {
                words.add(word)
            }
        }

        count++
    }

    println(count)
}

private fun partTwo() {
    val passwords = File(FILENAME).readLines()

    var count = 0

    password@for (password in passwords) {
        val words = HashSet<String>()

        for (word in password.split(" ")) {
            val sortedPassword = word.toCharArray().sorted().toString()

            if (sortedPassword in words) {
                continue@password
            } else {
                words.add(sortedPassword)
            }
        }

        count++
    }

    println(count)
}
