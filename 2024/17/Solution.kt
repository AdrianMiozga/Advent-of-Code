package `2024`.`17`

import `2024`.`17`.Opcode.ADV
import `2024`.`17`.Opcode.BDV
import `2024`.`17`.Opcode.BST
import `2024`.`17`.Opcode.BXC
import `2024`.`17`.Opcode.BXL
import `2024`.`17`.Opcode.CDV
import `2024`.`17`.Opcode.JNZ
import `2024`.`17`.Opcode.OUT
import aoc.utils.pr
import java.io.File
import kotlin.math.pow

private const val FILENAME = "2024/17/input.txt"

enum class Opcode(val code: Int) {
    ADV(0),
    BXL(1),
    BST(2),
    JNZ(3),
    BXC(4),
    OUT(5),
    BDV(6),
    CDV(7);

    companion object {
        fun fromCode(code: Int): Opcode? = Opcode.entries.find { it.code == code }
    }
}

fun main() {
    partOne()
}

private fun partOne() {
    val (registers, program) = File(FILENAME).readText().split("\r\n\r\n")

    var (registerA, registerB, registerC) = registers.split("\r\n").map { it.split(" ").last().toInt() }

    val output = mutableListOf<Int>()

    val instructions = program.split(" ").last().split(",").map { it.toInt() }
    var instructionPointer = 0

    while (instructionPointer < instructions.size) {
        val (opcode, inputOperand) = instructions.slice(instructionPointer..instructionPointer + 1)
            .let { (opcode, inputOperand) -> Opcode.fromCode(opcode) to inputOperand }

        val operand = when (opcode) {
            ADV, BST, OUT, BDV, CDV -> getComboOperand(inputOperand, registerA, registerB, registerC)
            else -> inputOperand
        }

        when (opcode) {
            ADV -> registerA = divide(registerA, operand)
            BXL -> registerB = registerB xor inputOperand
            BST -> registerB = operand % 8

            JNZ -> {
                if (registerA != 0) {
                    instructionPointer = inputOperand
                } else {
                    instructionPointer += 2
                }
            }

            BXC -> registerB = registerB xor registerC
            OUT -> output.add(operand % 8)
            BDV -> registerB = divide(registerA, operand)
            CDV -> registerC = divide(registerA, operand)
            else -> throw IllegalStateException("Invalid opcode")
        }

        if (opcode != JNZ) {
            instructionPointer += 2
        }
    }

    pr(output.joinToString(separator = ","))
}

fun getComboOperand(operand: Int, registerA: Int, registerB: Int, registerC: Int): Int {
    return when (operand) {
        in 0..3 -> operand
        4 -> registerA
        5 -> registerB
        6 -> registerC
        else -> throw IllegalStateException()
    }
}

private fun divide(numerator: Int, denominator: Int) =
    (numerator / (2.0.pow(denominator.toDouble()))).toInt()
