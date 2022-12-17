fun main() {
    fun List<String>.mapToCrateList(): List<MutableList<Char>> {
        val crates = mutableListOf<MutableList<Char>>()

        repeat((this[0].length+1) / 4) {
            crates.add(mutableListOf())
        }

        this.subList(0, this.indexOf("")-1).forEach {
            it.forEachIndexed { index, letter ->
                if (index % 4 == 1) {
                    if (letter != ' ') {
                        crates[(index-1)/4].add(letter)
                    }
                }
            }
        }

        crates.forEach {
            it.reverse()
        }

        return crates
    }

    fun List<String>.parseListOfMoves(): List<Move> {
        return this.subList(this.indexOf("")+1, this.size).map {
            val wordsList = it.split(" ")

            Move(wordsList[1].toInt(), wordsList[3].toInt(), wordsList[5].toInt())
        }
    }

    fun part1(input: List<String>): String {
        val crates = input.mapToCrateList().toMutableList()
        val moves = input.parseListOfMoves()

        for (move in moves) {
            repeat(move.amount) {
                val crate = crates[move.startPos-1].last()

                crates[move.startPos-1].removeLast()
                crates[move.endPos-1].add(crate)
            }
        }

        var finalString = ""
        crates.forEach { finalString += it.last() }

        return finalString
    }

    fun part2(input: List<String>): String {
        val crates = input.mapToCrateList().toMutableList()
        val moves = input.parseListOfMoves()

        for (move in moves) {
            for (i in 0 until move.amount) {
                val crateIndex = crates[move.startPos-1].dropLast(move.amount-i-1).lastIndex
                val crate = crates[move.startPos-1].dropLast(move.amount-i-1).last()

                crates[move.startPos-1].removeAt(crateIndex)
                crates[move.endPos-1].add(crate)
            }
        }

        var finalString = ""
        crates.forEach { finalString += it.last() }

        return finalString
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

data class Move(val amount: Int, val startPos: Int, val endPos: Int)