import java.io.File
import kotlin.math.abs

private const val FILENAME = "2020/12/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun Pair<Int, Int>.rotate(degrees: Int): Pair<Int, Int> {
    return when (degrees) {
        -270, 90 -> {
            Pair(-this.second, this.first)
        }

        -180, 180 -> {
            Pair(-this.first, -this.second)
        }

        -90, 270 -> {
            Pair(this.second, -this.first)
        }

        else -> {
            throw IllegalArgumentException("Invalid degrees: $degrees")
        }
    }
}

private fun partOne() {
    val lines = File(FILENAME).readLines()

    var shipPosition = Pair(0, 0)
    var direction = 90

    for (line in lines) {
        val (action, value) = Regex("""(\w)(\d+)""").matchEntire(line)!!.destructured

        when (action) {
            "F" -> {
                when (direction) {
                    0 -> {
                        shipPosition = Pair(
                            shipPosition.first,
                            shipPosition.second + value.toInt()
                        )
                    }

                    90 -> {
                        shipPosition = Pair(
                            shipPosition.first - value.toInt(),
                            shipPosition.second
                        )
                    }

                    180 -> {
                        shipPosition = Pair(
                            shipPosition.first,
                            shipPosition.second - value.toInt()
                        )
                    }

                    270 -> {
                        shipPosition = Pair(
                            shipPosition.first + value.toInt(),
                            shipPosition.second
                        )
                    }
                }
            }

            "L" -> {
                direction = (direction - value.toInt() + 360) % 360
            }

            "R" -> {
                direction = (direction + value.toInt() + 360) % 360
            }

            "N" -> {
                shipPosition = Pair(
                    shipPosition.first, shipPosition.second + value.toInt()
                )
            }

            "S" -> {
                shipPosition = Pair(
                    shipPosition.first, shipPosition.second - value.toInt()
                )
            }

            "E" -> {
                shipPosition = Pair(
                    shipPosition.first - value.toInt(), shipPosition.second
                )
            }

            "W" -> {
                shipPosition = Pair(
                    shipPosition.first + value.toInt(), shipPosition.second
                )
            }
        }
    }

    println(abs(shipPosition.first) + abs(shipPosition.second))
}

private fun partTwo() {
    val lines = File(FILENAME).readLines()

    var shipPosition = Pair(0, 0)
    var waypointPosition = Pair(-10, 1)

    for (line in lines) {
        val (action, value) = Regex("""(\w)(\d+)""").matchEntire(line)!!.destructured

        when (action) {
            "F" -> {
                shipPosition = Pair(
                    shipPosition.first + (value.toInt() * waypointPosition.first),
                    shipPosition.second + (value.toInt() * waypointPosition.second)
                )
            }

            "L" -> {
                waypointPosition = waypointPosition.rotate(-value.toInt())
            }

            "R" -> {
                waypointPosition = waypointPosition.rotate(value.toInt())
            }

            "N" -> {
                waypointPosition = Pair(
                    waypointPosition.first,
                    waypointPosition.second + value.toInt()
                )
            }

            "S" -> {
                waypointPosition = Pair(
                    waypointPosition.first,
                    waypointPosition.second - value.toInt()
                )
            }

            "E" -> {
                waypointPosition = Pair(
                    waypointPosition.first - value.toInt(),
                    waypointPosition.second
                )
            }

            "W" -> {
                waypointPosition = Pair(
                    waypointPosition.first + value.toInt(),
                    waypointPosition.second
                )
            }
        }
    }

    println(abs(shipPosition.first) + abs(shipPosition.second))
}
