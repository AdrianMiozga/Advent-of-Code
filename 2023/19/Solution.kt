package `2023`.`19`

import java.io.File
import java.util.*

private const val FILENAME = "2023/19/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val (workflows, ratings) = File(FILENAME).readText().split("\r\n\r\n")
        .map { it.split("\r\n") }

    var result = 0

    for (rating in ratings) {
        val map =
            rating.removeSurrounding("{", "}").split(",").associate {
                val (key, value) = it.split("=")
                key to value.toInt()
            }

        val sum = map.values.sum()

        val stack = Stack<String>()
        stack.push(workflows.find { it.substringBefore("{") == "in" })

        while (stack.isNotEmpty()) {
            val instructions =
                stack.pop().substringAfter("{").substringBefore("}").split(",")

            for (instruction in instructions) {
                if (instruction.all { it.isLetter() }) {
                    if (instruction == "A") {
                        result += sum
                    } else if (instruction != "R") {
                        stack.push(workflows.find {
                            it.substringBefore("{") == instruction
                        })
                    }

                    break
                }

                val (letter, sign, value, ret) = Regex("""(\w)([<>])(\d+):(\w+)""").matchEntire(
                    instruction
                )!!.destructured

                if ((sign == "<" && map[letter]!! < value.toInt()) || (sign == ">" && map[letter]!! > value.toInt())) {
                    if (ret == "A") {
                        result += sum
                    } else if (ret != "R") {
                        stack.push(workflows.find { it.substringBefore("{") == ret })
                    }

                    break
                }
            }
        }
    }

    println(result)
}
