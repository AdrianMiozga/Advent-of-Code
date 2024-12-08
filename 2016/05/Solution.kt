package `2016`.`05`

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

private const val FILENAME = "2016/05/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val doorID = File(FILENAME).readText()

    val messageDigest = MessageDigest.getInstance("MD5")
    var password = ""

    var i = 0

    repeat(8) {
        while (true) {
            i++
            val input = "$doorID$i"

            val md5 = BigInteger(
                1, messageDigest.digest(input.toByteArray())
            ).toString(16).padStart(32, '0')

            if (md5.startsWith("00000")) {
                password += md5[5]
                break
            }
        }
    }

    println(password)
}

private fun partTwo() {
    val doorID = File(FILENAME).readText()

    val messageDigest = MessageDigest.getInstance("MD5")
    val password = CharArray(8) { '_' }

    var i = 0

    repeat(8) {
        while (true) {
            i++
            val input = "$doorID$i"

            val md5 = BigInteger(
                1, messageDigest.digest(input.toByteArray())
            ).toString(16).padStart(32, '0')

            if (md5.startsWith("00000") && md5[5].isDigit()) {
                val position = md5[5].digitToInt()

                if (position <= 7 && password[position] == '_') {
                    password[position] = md5[6]
                    break
                }
            }
        }
    }

    println(password)
}
