private val mapping = mapOf(
    'A' to 12,
    'K' to 11,
    'Q' to 10,
    'T' to 9,
    '9' to 8,
    '8' to 7,
    '7' to 6,
    '6' to 5,
    '5' to 4,
    '4' to 3,
    '3' to 2,
    '2' to 1,
    'J' to 0,
)

data class HandTwo(
    val type: String, val bid: String, val rank: Int
) : Comparable<HandTwo> {
    override fun compareTo(other: HandTwo): Int {
        if (rank < other.rank) {
            return -1
        } else if (rank > other.rank) {
            return 1
        }

        for (index in type.indices) {
            if (mapping[type[index]]!! > mapping[other.type[index]]!!) {
                return 1
            } else if (mapping[type[index]]!! < mapping[other.type[index]]!!) {
                return -1
            }
        }

        return 0
    }
}
