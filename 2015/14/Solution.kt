import java.io.File

private const val FILENAME = "2015/14/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val reindeerList = File(FILENAME).readLines()

    var max = 0

    for (reindeer in reindeerList) {
        val (speed, time, rest) = Regex("""\w+ can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""").matchEntire(
            reindeer
        )!!.destructured.toList().map { it.toInt() }

        var currentDistance = 0
        var currentTime = 0
        var isResting = false
        var remainingRest = rest
        var remainingMovement = time

        while (currentTime != 2503) {
            currentTime++

            if (isResting) {
                remainingRest--
            } else {
                currentDistance += speed
                remainingMovement--
            }

            if (remainingMovement == 0) {
                isResting = true
                remainingMovement = time
            } else if (remainingRest == 0) {
                isResting = false
                remainingRest = rest
            }
        }

        if (currentDistance > max) {
            max = currentDistance
        }
    }

    println(max)
}
