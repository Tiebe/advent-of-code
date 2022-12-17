fun main() {
    fun part1(input: String): Int {
        input.forEachIndexed { index, _ ->
            if (index > 3) {
                val letterList = listOf(input[index-3], input[index-2], input[index-1], input[index])

                if (letterList.groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty()) return index + 1
            }
        }

        return 0
    }

    fun part2(input: String): Int {
        input.forEachIndexed { index, _ ->
            if (index > 13) {
                val letterList = mutableListOf<Char>()

                repeat(14) {
                    letterList.add(input[index-it-1])
                }

                if (letterList.groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty()) return index
            }
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")[0]
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")[0]
    part1(input).println()
    part2(input).println()
}