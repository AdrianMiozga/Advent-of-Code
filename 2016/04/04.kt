import java.io.File

private const val FILENAME = "2016/04/input.txt"
private val REGEX = Regex("""([a-z\-]+)(\d+)\[([a-z]+)]""")

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    var sum = 0
    val lines = File(FILENAME).readLines()

    for (line in lines) {
        val (encryptedName, sectorID, checksum) = REGEX.matchEntire(line)!!.destructured

        val map = encryptedName.replace("-", "").toList().sorted()
           .groupingBy { it }.eachCount()

        val result = map.toList().sortedByDescending { (_, value) -> value }
            .toMap().keys.take(5).joinToString(separator = "")

        if (result == checksum) {
            sum += sectorID.toInt()
        }
    }

    println(sum)
}

private fun partTwo() {
    val lines = File(FILENAME).readLines()

    for (line in lines) {
        val (encryptedName, sectorID, _) = REGEX.matchEntire(line)!!.destructured

        var decryptedName = ""
        for (char in encryptedName.replace("-", " ").toCharArray()) {
            if (char == ' ') {
                decryptedName += char
                continue
            }

            val newAscii = (char.code - 97 + sectorID.toInt()) % 26 + 97
            decryptedName += newAscii.toChar()
        }

        if (decryptedName.contains(("northpole"))) {
            println(sectorID)
        }
    }
}
