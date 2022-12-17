fun main() {
    fun part1(input: List<String>): Int {
        return input.count { sectionList ->
            val sections = sectionList.split(',')

            val section1 = sections[0].split('-').map { it.toInt() }
            val section2 = sections[1].split('-').map { it.toInt() }

            ((section1[0] >= section2[0] && section1[1] <= section2[1]) || (section1[0] <= section2[0] && section1[1] >= section2[1]))
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { sectionList ->
            val sections = sectionList.split(',')

            val section1 = sections[0].split('-').map { it.toInt() }
            val section2 = sections[1].split('-').map { it.toInt() }

            ((section1[0] >= section2[0] && section1[0] <= section2[1]) ||
                    (section1[1] >= section2[0] && section1[1] <= section2[1])) ||

                ((section2[0] >= section1[0] && section2[0] <= section1[1]) ||
                    (section2[1] >= section1[0] && section2[1] <= section1[1]))
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}