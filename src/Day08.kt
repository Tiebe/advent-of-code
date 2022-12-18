fun main() {
    fun List<String>.toMatrix(): Array<IntArray> = this.map { line -> line.map { it.digitToInt() }.toIntArray() }.toTypedArray()

    fun Array<IntArray>.getTopVisibility(x: Int, y: Int): Int {
        val pointValue = this[y][x]

        for (i in y-1 downTo 0) {
            if (this[i][x] >= pointValue) {
                return y-i-1
            }
        }

        return y
    }

    fun Array<IntArray>.getBottomVisibility(x: Int, y: Int): Int {
        val pointValue = this[y][x]

        for (i in y+1 until size) {
            if (this[i][x] >= pointValue) {
                return i-y
            }
        }

        return size-y-1
    }

    fun Array<IntArray>.getLeftVisibility(x: Int, y: Int): Int {
        val pointValue = this[y][x]

        for (i in x-1 downTo 0) {
            if (this[y][i] >= pointValue) {
                return x-i
            }
        }

        return x
    }

    fun Array<IntArray>.getRightVisibility(x: Int, y: Int): Int {
        val pointValue = this[y][x]

        for (i in x+1 until size) {
            if (this[y][i] >= pointValue) {
                return i-x
            }
        }

        return size-x-1
    }

    fun part1(input: List<String>): Int {
        val matrix = input.toMatrix()

        matrix.forEach { it.forEach(::print); println() }

        matrix.getRightVisibility(1, 0).println()
        matrix.getRightVisibility(1, 2).println()

        return 0
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

