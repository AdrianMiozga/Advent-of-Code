package `2023`.`16`

import java.io.File

private const val FILENAME = "2023/16/input.txt"

data class Beam(var x: Int, var y: Int, var direction: Int)

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val layout = File(FILENAME).readLines().map { it.toCharArray() }

    val beams = mutableSetOf<Beam>()
    val beamsToAdd = mutableSetOf<Beam>()
    val positions = hashSetOf<Pair<Int, Int>>()

    beams.add(Beam(0, 0, 90))
    positions.add(Pair(0, 0))

    var counter = 0
    var probe = 0

    while (true) {
        for (beam in beams) {
            val tile = layout[beam.y][beam.x]

            if ((beam.direction == 90 || beam.direction == 270) && tile == '|') {
                beam.direction = 0
                beamsToAdd.add(Beam(beam.x, beam.y, 180))
            }

            if ((beam.direction == 0 || beam.direction == 180) && tile == '-') {
                beam.direction = 90
                beamsToAdd.add(Beam(beam.x, beam.y, 270))
            }

            if (tile == '/') {
                when (beam.direction) {
                    0 -> beam.direction = 90
                    90 -> beam.direction = 0
                    180 -> beam.direction = 270
                    270 -> beam.direction = 180
                }
            }

            if (tile == '\\') {
                when (beam.direction) {
                    0 -> beam.direction = 270
                    90 -> beam.direction = 180
                    180 -> beam.direction = 90
                    270 -> beam.direction = 0
                }
            }

            when (beam.direction) {
                0 -> beam.y--
                90 -> beam.x++
                180 -> beam.y++
                270 -> beam.x--
            }

            if (beam.x < 0 || beam.x >= layout[0].size) {
                continue
            }

            if (beam.y < 0 || beam.y >= layout.size) {
                continue
            }

            positions.add(Pair(beam.x, beam.y))
        }

        beams.removeAll { it.x < 0 || it.x >= layout[0].size }
        beams.removeAll { it.y < 0 || it.y >= layout.size }

        beams.addAll(beamsToAdd)
        beamsToAdd.clear()

        counter++

        if (positions.size == probe) {
            break
        }

        if (counter % 25 == 0) {
            probe = positions.size
        }
    }

    println(positions.size)
}

private fun partTwo() {
    val layout = File(FILENAME).readLines().map { it.toCharArray() }

    val startBeams = mutableListOf<Beam>()

    for (index in layout.indices) {
        startBeams.add(Beam(index, 0, 180))
        startBeams.add(Beam(index, layout.size - 1, 0))
        startBeams.add(Beam(0, index, 90))
        startBeams.add(Beam(layout[0].size - 1, index, 270))
    }

    var max = 0

    for (startBeam in startBeams) {
        val beams = mutableSetOf<Beam>()
        val beamsToAdd = mutableSetOf<Beam>()
        val positions = hashSetOf<Pair<Int, Int>>()

        beams.add(startBeam)
        positions.add(Pair(startBeam.x, startBeam.y))

        var counter = 0
        var probe = 0

        while (true) {
            for (beam in beams) {
                val tile = layout[beam.y][beam.x]

                if ((beam.direction == 90 || beam.direction == 270) && tile == '|') {
                    beam.direction = 0
                    beamsToAdd.add(Beam(beam.x, beam.y, 180))
                }

                if ((beam.direction == 0 || beam.direction == 180) && tile == '-') {
                    beam.direction = 90
                    beamsToAdd.add(Beam(beam.x, beam.y, 270))
                }

                if (tile == '/') {
                    when (beam.direction) {
                        0 -> beam.direction = 90
                        90 -> beam.direction = 0
                        180 -> beam.direction = 270
                        270 -> beam.direction = 180
                    }
                }

                if (tile == '\\') {
                    when (beam.direction) {
                        0 -> beam.direction = 270
                        90 -> beam.direction = 180
                        180 -> beam.direction = 90
                        270 -> beam.direction = 0
                    }
                }

                when (beam.direction) {
                    0 -> beam.y--
                    90 -> beam.x++
                    180 -> beam.y++
                    270 -> beam.x--
                }

                if (beam.x < 0 || beam.x >= layout[0].size) {
                    continue
                }

                if (beam.y < 0 || beam.y >= layout.size) {
                    continue
                }

                positions.add(Pair(beam.x, beam.y))
            }

            beams.removeAll { it.x < 0 || it.x >= layout[0].size }
            beams.removeAll { it.y < 0 || it.y >= layout.size }

            beams.addAll(beamsToAdd)
            beamsToAdd.clear()

            counter++

            if (positions.size == probe) {
                break
            }

            if (counter % 25 == 0) {
                probe = positions.size
            }

            if (max < positions.size) {
                max = positions.size
            }
        }
    }

    println(max)
}
