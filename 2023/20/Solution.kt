package `2023`.`20`

import java.io.File
import java.util.*

private const val FILENAME = "2023/20/input.txt"
private const val REGEX = """(.\w+) -> (.+)"""

private data class Signal(
    var source: String, var pulse: String, var destination: String
) {
    override fun toString(): String {
        return "$source -$pulse-> $destination"
    }
}

fun main() {
    partOne()
}

private fun partOne() {
    val moduleConfiguration = File(FILENAME).readLines()

    val flipFlops = HashMap<String, Boolean>()
    val conjunctions = HashMap<String, HashMap<String, Boolean>>()

    for (module in moduleConfiguration) {
        val matchResult = Regex(REGEX).matchEntire(module)!!

        val source = matchResult.groupValues[1]
        val destinations = matchResult.groupValues[2].split(", ")

        val stripSource =
            source.substringBefore(" ").removeModuleSymbol()

        if (module.startsWith("%")) {
            flipFlops[stripSource] = false
        }

        for (destination in destinations) {
            if (moduleConfiguration.isDestinationConjunction(destination)) {
                if (conjunctions[destination] == null) {
                    conjunctions[destination] = hashMapOf(stripSource to false)
                } else {
                    conjunctions[destination]?.put(stripSource, false)
                }
            }
        }
    }

    var lowPulses = 0
    var highPulses = 0

    repeat(1000) {
        val queue: Queue<Signal> = LinkedList()

        queue.add(Signal("button", "low", "broadcaster"))
        lowPulses++

        while (queue.isNotEmpty()) {
            val signal = queue.remove()

            val line = moduleConfiguration.findPulse(signal.destination)

            if (line.isEmpty()) {
                continue
            }

            val matchResult = Regex(REGEX).matchEntire(line)!!

            val source = matchResult.groupValues[1]
            val destinations = matchResult.groupValues[2].split(", ")

            for (destination in destinations) {
                val stripSource = source.removeModuleSymbol()

                val pulse = if (source.startsWith("%")) {
                    if (flipFlops[stripSource]!!) {
                        "high"
                    } else {
                        "low"
                    }
                } else if (source.startsWith("&")) {
                    if (conjunctions[stripSource]!!.all { it.value }) {
                        "low"
                    } else {
                        "high"
                    }
                } else {
                    "low"
                }

                val nextSignal = Signal(stripSource, pulse, destination)

                if (pulse == "low") {
                    lowPulses++
                } else {
                    highPulses++
                }

                if (moduleConfiguration.isDestinationFlipFlop(destination)) {
                    if (pulse == "low") {
                        flipFlops[destination] = !flipFlops[destination]!!
                    } else {
                        continue
                    }
                }

                if (moduleConfiguration.isDestinationConjunction(destination)) {
                    conjunctions[destination]!![stripSource] = pulse == "high"
                }

                queue.add(nextSignal)
            }
        }
    }

    println(lowPulses * highPulses)
}

private fun List<String>.findPulse(destination: String): String {
    for (module in this) {
        if (module.substringBefore(" ").removeModuleSymbol() == destination) {
            return module
        }
    }

    return ""
}

private fun String.removeModuleSymbol(): String {
    return if (startsWith("%") || startsWith("&")) {
        removeRange(0 until 1)
    } else {
        this
    }
}

private fun List<String>.isDestinationFlipFlop(destination: String): Boolean {
    for (module in this) {
        if (module.substringBefore(" ") == "%$destination") {
            return true
        }
    }

    return false
}

private fun List<String>.isDestinationConjunction(destination: String): Boolean {
    for (module in this) {
        if (module.substringBefore(" ") == "&$destination") {
            return true
        }
    }

    return false
}
