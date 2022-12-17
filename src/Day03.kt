fun main() {
    fun part1(input: List<String>): Int {
        var total = 0

        for (bag in input) {
            val firstHalf = bag.substring(0, bag.length/2)
            val secondHalf = bag.substring(bag.length/2)

            for (it in firstHalf) {
                if (it in secondHalf) {
                    total += if (it.isUpperCase()) {
                        it.code - 65 + 26 + 1
                    } else {
                        it.code - 97 + 1
                    }
                    break
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val letters = mutableListOf<Char>()

        val groups = mutableListOf<List<String>>()

        val groupList = mutableListOf<String>()
        input.forEachIndexed { index, bag ->
            groupList.add(bag)

            if ((index+1) % 3 == 0) {
                groups.add(groupList.toMutableList())
                groupList.clear()
            }
        }

        groups.forEach {
            for (letter in it[0]) {
                if (letter in it[1] && letter in it[2]) {
                    letters.add(letter)
                    break
                }
            }
        }

        var total = 0

        letters.forEach {
            total += if (it.isUpperCase()) {
                it.code - 65 + 26 + 1
            } else {
                it.code - 97 + 1
            }
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}