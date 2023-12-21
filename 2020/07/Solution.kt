import java.io.File

private const val FILENAME = "2020/07/input.txt"

fun main() {
    partOne()
}

private fun partOne() {
    val file = File(FILENAME).readLines()
    val mapping = hashMapOf<String, List<String>>()

    for (line in file) {
        val (name) = Regex("""(\w+ \w+)""").matchAt(line, 0)!!.destructured

        if (name == "shiny gold") {
            continue
        }

        val bags = mutableListOf<String>()

        for (element in Regex("""\d+ (\w+ \w+) bag""").findAll(line)) {
            bags.add(element.groups[1]!!.value)
        }

        mapping[name] = bags
    }

    println(mapping)

    var result = 0
    for (element in mapping.keys) {
        result += count(mapping, element)
    }

    println(result)
}

private fun count(map: HashMap<String, List<String>>, key: String): Int {
    if (key == "shiny gold") {
        return 1
    }

    var inner = 0

    for (keys in map[key]!!) {
        inner += count(map, keys)
    }

    return if (inner > 0) {
        1
    } else {
        0
    }
}
