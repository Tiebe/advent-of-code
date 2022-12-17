fun main() {
    fun checkRPSWin(input: List<String>): Boolean {
        return (input[0] == "A" && input[1] == "B") ||
                (input[0] == "B" && input[1] == "C") ||
                (input[0] == "C" && input[1] == "A")
    }

    fun String.mapXtoA(): String {
        return when (this) {
            "X" -> "A"
            "Y" -> "B"
            "Z" -> "C"
            else -> this
        }
    }

    fun String.mapAtoX(): String {
        return when (this) {
            "A" -> "X"
            "B" -> "Y"
            "C" -> "Z"
            else -> this
        }
    }

    fun calculateRPSScore(pair: List<String>): Int {
        var score = 0

        if (pair[1] == "A") score += 1
        if (pair[1] == "B") score += 2
        if (pair[1] == "C") score += 3

        if (pair[0] == pair[1]) score += 3
        if (checkRPSWin(pair)) score += 6

        return score
    }

    fun part1(input: List<String>): Int {
        var score = 0

        input.forEach { inputString ->
            val pair = inputString.split(" ").map {
                when (it) {
                    "X" -> "A"
                    "Y" -> "B"
                    "Z" -> "C"
                    else -> it
                }
            }

            score += calculateRPSScore(pair)
        }

        return score
    }

    fun part2(input: List<String>): Int {
        val newList = mutableListOf<String>()

        input.forEach { inputString ->
            val pair = inputString.split(" ").map {
                when (it) {
                    "X" -> "A"
                    "Y" -> "B"
                    "Z" -> "C"
                    else -> it
                }
            }

            if (pair[1] == "A") {
                newList.add("${pair[0]} ${
                    when (pair[0]) {
                        "A" -> "C"
                        "B" -> "A"
                        "C" -> "B"
                        else -> "Z"
                    }
                }")
            } else if (pair[1] == "B") {
                newList.add("${pair[0]} ${pair[0].mapAtoX()}")
            } else if (pair[1] == "C") {
                newList.add("${pair[0]} ${
                    when (pair[0]) {
                        "A" -> "B"
                        "B" -> "C"
                        "C" -> "A"
                        else -> "Z"
                    }
                }")
            }
        }

        return part1(newList)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}