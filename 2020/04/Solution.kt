package `2020`.`04`

import java.io.File

private const val FILENAME = "2020/04/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val passports = File(FILENAME)
        .readText()
        .trim()
        .split("\r\n\r\n")
        .map { it.replace("\r\n", " ") }

    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    // Count good passports
    val goodPassports = passports.count { passport ->
        requiredFields.all { field ->
            passport.contains(field)
        }
    }

    println(goodPassports)
}

private fun partTwo() {
    val passports = File(FILENAME)
        .readText()
        .trim()
        .split("\r\n\r\n")
        .map { it.replace("\r\n", " ") }

    // Count good passports
    var goodPassports = 0

    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val correctEyeColors =
        listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    passportLoop@ for (passport in passports) {
        // Check if all fields are present
        if (!requiredFields.all { passport.contains(it) }) {
            continue
        }

        val fields = passport.split(" ")

        // Check if fields contain correct values
        for (field in fields) {
            val key = field.slice(0..2)
            val value = field.slice(4 until field.length)

            when (key) {
                "byr" -> if (value.toIntOrNull() !in 1920..2002) continue@passportLoop
                "iyr" -> if (value.toIntOrNull() !in 2010..2020) continue@passportLoop
                "eyr" -> if (value.toIntOrNull() !in 2020..2030) continue@passportLoop
                "hgt" -> {
                    val regex = Regex("""^(\d+)(cm|in)$""")
                    val (number, unit) = regex.find(value)?.destructured
                        ?: continue@passportLoop

                    when (unit) {
                        "cm" -> if (number.toIntOrNull() !in 150..193) continue@passportLoop
                        "in" -> if (number.toIntOrNull() !in 59..76) continue@passportLoop
                    }
                }

                "hcl" -> if (!(value matches Regex("""^#[0-9a-f]{6}$"""))) continue@passportLoop
                "ecl" -> if (!correctEyeColors.any { value == it }) continue@passportLoop
                "pid" -> if (!(value matches Regex("""^\d{9}$"""))) continue@passportLoop
            }
        }

        goodPassports++
    }

    println(goodPassports)
}
