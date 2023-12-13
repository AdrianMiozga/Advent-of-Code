import java.io.File

private const val FILENAME = "2023/05/input.txt"
private const val regex = """(\d+) (\d+) (\d+)"""

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val seeds = file[0].slice(7 until file[0].length).split(" ").map { it.toLong() }

    var result = Long.MAX_VALUE

    for (seed in seeds) {
        var transformed = seed
        var skip = false

        for (line in file.slice(2 until file.size)) {
            if (line.contains("map")) {
                continue
            }

            if (line.isBlank()) {
                if (skip) {
                    skip = false
                }

                continue
            }

            if (skip) {
                continue
            }

            val (destination, source, range) = Regex(regex).matchEntire(
                line
            )!!.destructured.toList().map { it.toLong() }

            if (transformed >= source && transformed < source + range) {
                transformed = transformed - source + destination
                skip = true
            }
        }

        if (transformed < result) {
            result = transformed
        }
    }

    println(result)
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val seeds = file[0].slice(7 until file[0].length).split(" ").map { it.toLong() }

    for (location in 0 until Long.MAX_VALUE) {
        var transformed = location
        var skip = false

        for (line in file.reversed()) {
            if (line.contains("map") || line.contains("seeds")) {
                continue
            }

            if (line.isBlank()) {
                if (skip) {
                    skip = false
                }

                continue
            }

            if (skip) {
                continue
            }

            val (destination, source, range) = Regex(regex).matchEntire(
                line
            )!!.destructured.toList().map { it.toLong() }

            if (transformed >= destination && transformed < destination + range) {
                transformed = transformed - destination + source
                skip = true
            }
        }

        for (element in seeds.indices step 2) {
            if (transformed >= seeds[element] && transformed < seeds[element] + seeds[element + 1]) {
                println(location)
                return
            }
        }
    }
}
