fun main() {
    fun part1(input: List<String>): MutableList<Int> {
        val sumList = mutableListOf(0)
        var index = 0

        input.forEach {
            if (it == "") { index++; sumList.add(0) }
            else {
                sumList[index] += it.toInt()
            }
        }

        return sumList
    }

    fun part2(input: List<String>): Int {
        val sumList = part1(input)

        var sum = 0

        repeat(3) {
            sum += sumList.maxOrNull() ?: 0
            sumList.remove(sumList.maxOrNull() ?: 0)
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check((part1(testInput).maxOrNull() ?: 0) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    (part1(input).maxOrNull() ?: 0).println()
    part2(input).println()
}