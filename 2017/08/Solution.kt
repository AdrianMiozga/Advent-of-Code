package `2017`.`08`

import java.io.File

private const val FILENAME = "2017/08/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val instructions = File(FILENAME).readLines()

    val registers = HashMap<String, Int>()

    for (instruction in instructions) {
        val (registerPart, conditionPart) = instruction.split(" if ")

        val (register, action, value) = registerPart.split(" ")
        val (ifRegister, sign, ifValue) = conditionPart.split(" ")

        if (registers[register] == null) {
            registers[register] = 0
        }

        if (registers[ifRegister] == null) {
            registers[ifRegister] = 0
        }

        var condition = false

        when (sign) {
            ">" -> condition = registers[ifRegister]!! > ifValue.toInt()
            "<" -> condition = registers[ifRegister]!! < ifValue.toInt()
            ">=" -> condition = registers[ifRegister]!! >= ifValue.toInt()
            "<=" -> condition = registers[ifRegister]!! <= ifValue.toInt()
            "==" -> condition = registers[ifRegister]!! == ifValue.toInt()
            "!=" -> condition = registers[ifRegister]!! != ifValue.toInt()
        }

        if (condition) {
            if (action == "inc") {
                registers[register] = registers[register]!! + value.toInt()
            } else if (action == "dec") {
                registers[register] = registers[register]!! - value.toInt()
            }
        }
    }

    println(registers.values.max())
}

private fun partTwo() {
    val instructions = File(FILENAME).readLines()

    val registers = HashMap<String, Int>()

    var max = 0

    for (instruction in instructions) {
        val (registerPart, conditionPart) = instruction.split(" if ")

        val (register, action, value) = registerPart.split(" ")
        val (ifRegister, sign, ifValue) = conditionPart.split(" ")

        if (registers[register] == null) {
            registers[register] = 0
        }

        if (registers[ifRegister] == null) {
            registers[ifRegister] = 0
        }

        var condition = false

        when (sign) {
            ">" -> condition = registers[ifRegister]!! > ifValue.toInt()
            "<" -> condition = registers[ifRegister]!! < ifValue.toInt()
            ">=" -> condition = registers[ifRegister]!! >= ifValue.toInt()
            "<=" -> condition = registers[ifRegister]!! <= ifValue.toInt()
            "==" -> condition = registers[ifRegister]!! == ifValue.toInt()
            "!=" -> condition = registers[ifRegister]!! != ifValue.toInt()
        }

        if (condition) {
            if (action == "inc") {
                registers[register] = registers[register]!! + value.toInt()
            } else if (action == "dec") {
                registers[register] = registers[register]!! - value.toInt()
            }
        }

        if (registers[register]!!.toInt() > max) {
            max = registers[register]!!.toInt()
        }
    }

    println(max)
}
