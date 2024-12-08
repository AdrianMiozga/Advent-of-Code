package `2020`.`14`

import java.io.File
import kotlin.collections.iterator

private const val FILENAME = "2020/14/input.txt"
private val pattern = Regex("""mem\[(\d+)] = (\d+)""")


fun main() {
    partOne()
}

private fun partOne() {
    val lines = File(FILENAME).readLines()

    val memoryAddresses: MutableMap<String, String> = mutableMapOf()

    var mask = ""

    for (line in lines) {
        if (line.startsWith("mask")) {
            mask = line.substring(7)
        } else {
            val (memoryAddress, value) = pattern.matchEntire(line)!!.destructured

            val bin = Integer.toBinaryString(value.toInt()).padStart(36, '0')

            var result = ""

            for ((index, char) in mask.withIndex()) {
                when (char) {
                    'X' -> {
                        result += bin[index]
                    }

                    '0' -> {
                        result += "0"
                    }

                    '1' -> {
                        result += "1"
                    }
                }
            }

            memoryAddresses[memoryAddress] = result.toLong(2).toString()
        }
    }

    var result = 0L

    for (element in memoryAddresses) {
        result += element.value.toLong()
    }

    print(result)
}
