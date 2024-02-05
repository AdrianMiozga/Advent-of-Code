import java.io.File

private const val FILENAME = "2015/14/input.txt"

data class Reindeer(
    val name: String,
    val speed: Int,
    val time: Int,
    val rest: Int,
    var currentDistance: Int = 0,
    var isResting: Boolean = false,
    var remainingRest: Int = rest,
    var remainingMovement: Int = time,
    var points: Int = 0
)

fun main() {
    partOne()
    partTwo()
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

private fun partTwo() {
    val reindeerList = File(FILENAME).readLines()

    val reindeerListClass: MutableList<Reindeer> = mutableListOf()

    for (reindeer in reindeerList) {
        val (name, speed, time, rest) = Regex("""(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""").matchEntire(
            reindeer
        )!!.destructured

        reindeerListClass.add(Reindeer(name, speed.toInt(), time.toInt(), rest.toInt()))
    }

    var currentTime = 0

    while (currentTime != 2503) {
        currentTime++

        for (reindeer in reindeerListClass) {
            if (reindeer.isResting) {
                reindeer.remainingRest--
            } else {
                reindeer.currentDistance += reindeer.speed
                reindeer.remainingMovement--
            }

            if (reindeer.remainingMovement == 0) {
                reindeer.isResting = true
                reindeer.remainingMovement = reindeer.time
            } else if (reindeer.remainingRest == 0) {
                reindeer.isResting = false
                reindeer.remainingRest = reindeer.rest
            }
        }

        reindeerListClass.filter { it.currentDistance == reindeerListClass.maxBy { it.currentDistance }.currentDistance }
            .map { it.points++ }
    }

    println(reindeerListClass.maxBy { it.points }.points)
}
