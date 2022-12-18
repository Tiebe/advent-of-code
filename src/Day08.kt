fun main() {
    fun part1(input: List<String>): Int {
        var visibleCount = input[0].length * 2 + input.size * 2 - 4

        for (treesIndex in input.subList(1, input.size - 1).indices) {
            val trees = input[treesIndex+1]

            treeLoop@ for (treeSelectIndex in trees.indices) {
                if (treeSelectIndex == 0 || treeSelectIndex == trees.length-1) continue@treeLoop

                //check left
                if (trees.subSequence(0, treeSelectIndex).all {
                    it < trees[treeSelectIndex]
                }) visibleCount++

                // check right
                else if (trees.subSequence(treeSelectIndex+1, trees.length).all {
                    it < trees[treeSelectIndex]
                }) visibleCount++

                //check top
                else if (input.subList(0, treesIndex+1).all {
                        it[treeSelectIndex] < trees[treeSelectIndex]
                }) visibleCount++

                // check bottom
                else if (input.subList(treesIndex+2, input.size).all {
                        it[treeSelectIndex] < trees[treeSelectIndex]
                }) visibleCount++
            }
        }

        return visibleCount
    }

    fun part2(input: List<String>): Int {
        var highestScore = 0

        for (treesIndex in input.indices) {
            val trees = input[treesIndex]

            treeLoop@ for (treeSelectIndex in trees.indices) {
                if (treesIndex != 3) continue@treeLoop
                if (treeSelectIndex != 2) continue@treeLoop

                var score = 1

                var starting = score
                //check left
                for (i in 0 until treeSelectIndex) {
                    val check = treeSelectIndex - i - 1

                    if (trees[check] >= trees[treeSelectIndex]) {
                        score *= i

                        println("$trees: ${trees[check]}: score: $i")
                        break
                    }
                }

                if (starting == score) {
                    score *= treeSelectIndex
                }

                starting = score
                // check right
                for (i in (treeSelectIndex+1) until trees.length) {
                    if (trees[i] >= trees[treeSelectIndex]) {
                        score *= (i-treeSelectIndex)

                        break
                    }
                }

                if (starting == score) {
                    score *= (trees.length - treeSelectIndex)
                }

                starting = score

                // check top
                for (i in 0 until treesIndex) {
                    val check = treesIndex - i - 1

                    if (input[check][treeSelectIndex] >= trees[treeSelectIndex]) {
                        score *= (i+1)
                    }
                }

                if (starting == score) {
                    score *= (input.size - treesIndex)
                }

                starting = score

                for (i in (treesIndex+1) until input.size) {
                    if (input[i][treeSelectIndex] >= trees[treeSelectIndex]) {
                        score *= (i-treesIndex)
                    }
                }

                if (starting == score) {
                    score *= (input.size - treesIndex)
                }


                println(score)
                if (score > highestScore) highestScore = score


            }
        }

        println(highestScore)



        return highestScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

