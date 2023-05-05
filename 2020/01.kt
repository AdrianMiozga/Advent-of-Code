import java.io.File

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    // O(n^2)
    val lines = File("2020/01.txt").readLines().map(String::toInt)

    for (first in lines) {
        for (second in lines) {
            if (first + second == 2020) {
                println(first * second)
                return
            }
        }
    }
}

fun partTwo() {
    // O(n^3)
    val lines = File("2020/01.txt").readLines().map(String::toInt)

    for (first in lines) {
        for (second in lines) {
            for (third in lines) {
                if (first + second + third == 2020) {
                    println(first * second * third)
                    return
                }
            }
        }
    }
}
