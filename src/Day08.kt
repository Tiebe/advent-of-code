@file:Suppress("DuplicatedCode")

fun main() {
    fun List<String>.toMatrix(): Array<IntArray> = this.map { line -> line.map { it.digitToInt() }.toIntArray() }.toTypedArray()

    fun Array<IntArray>.getTopVisibility(x: Int, y: Int): Pair<Boolean, Int> {
        val pointValue = this[y][x]

        for (i in y-1 downTo 0) {
            if (this[i][x] >= pointValue) {
                return false to y-i
            }
        }

        return true to y
    }

    fun Array<IntArray>.getBottomVisibility(x: Int, y: Int): Pair<Boolean, Int> {
        val pointValue = this[y][x]

        for (i in y+1 until size) {
            if (this[i][x] >= pointValue) {
                return false to i-y
            }
        }

        return true to size-y-1
    }

    fun Array<IntArray>.getLeftVisibility(x: Int, y: Int): Pair<Boolean, Int> {
        val pointValue = this[y][x]

        for (i in x-1 downTo 0) {
            if (this[y][i] >= pointValue) {
                return false to x-i
            }
        }

        return true to x
    }

    fun Array<IntArray>.getRightVisibility(x: Int, y: Int): Pair<Boolean, Int> {
        val pointValue = this[y][x]

        for (i in x+1 until size) {
            if (this[y][i] >= pointValue) {
                return false to i-x
            }
        }

        return true to size-x-1
    }

    fun part1(input: List<String>): Int {
        val matrix = input.toMatrix()

        var visible = matrix.size*4-4

        for (line in 1 until matrix.size-1) {
            for (tree in 1 until matrix.size-1) {
                if (matrix.getLeftVisibility(tree, line).first ||
                    matrix.getRightVisibility(tree, line).first ||
                    matrix.getTopVisibility(tree, line).first ||
                    matrix.getBottomVisibility(tree, line).first)
                    visible++
            }
        }
        return visible
    }

    fun part2(input: List<String>): Int {
        val matrix = input.toMatrix()
        var topScenicScore = 0

        for (line in 1 until matrix.size-1) {
            for (tree in 1 until matrix.size-1) {
                var scenicScore = 1

                scenicScore *=
                    (matrix.getTopVisibility(tree, line).second *
                        matrix.getRightVisibility(tree, line).second *
                        matrix.getBottomVisibility(tree, line).second *
                        matrix.getLeftVisibility(tree, line).second)

                if (scenicScore > topScenicScore) topScenicScore = scenicScore
            }
        }

        return topScenicScore
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

