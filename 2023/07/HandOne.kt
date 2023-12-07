private val mapping = mapOf(
    'A' to 12,
    'K' to 11,
    'Q' to 10,
    'J' to 9,
    'T' to 8,
    '9' to 7,
    '8' to 6,
    '7' to 5,
    '6' to 4,
    '5' to 3,
    '4' to 2,
    '3' to 1,
    '2' to 0,
)

data class HandOne(
    val type: String, val bid: String, val rank: Int
) : Comparable<HandOne> {
    override fun compareTo(other: HandOne): Int {
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
