fun main() {
    fun List<String>.parseFilesystem(): Directory {
        val structure = Directory("")
        var currentDirectory = ""

        for (line in this) {
            if (line.startsWith("$ ")) {
                val command = line.replace("$ ", "")

                if (command == "cd /") currentDirectory = ""
                else if (command.startsWith("cd")) {
                    val newDirectory = command.replace("cd ", "")
                    currentDirectory = if (newDirectory == "..") {
                        currentDirectory.replaceAfterLast("/", "", "").removeSuffix("/")
                    } else currentDirectory.plus("/$newDirectory").removePrefix("/")
                }
            } else {
                val directoryPath = currentDirectory.split("/").filter { it.isNotEmpty() }
                var directory = structure

                directoryPath.forEachIndexed { _, s ->
                    directory = directory.directories[directory.directories.indexOfFirst {
                        it.name == s
                    }]
                }

                val file = line.split(" ")
                if (file[0] == "dir") {
                    if (directory.directories.none { it.name == file[1] }) {
                        directory.directories.add(Directory(file[1]))
                    }
                } else {
                    directory.files.add(File(file[1], file[0].toInt()))
                }
            }
        }

        return structure
    }

    fun Directory.getDirectoryList(directories: MutableList<Directory> = mutableListOf()): MutableList<Directory> {
        directories.add(this)
        for (directory in this.directories) {
            directory.getDirectoryList(directories)
        }

        return directories
    }

    fun Directory.getDirectorySize(): Int {
        var size = 0

        for (file in this.files) {
            size += file.size
        }

        for (directory in this.directories) {
            size += directory.getDirectorySize()
        }

        return size
    }

    fun part1(input: List<String>): Int {
        val directoryStructure = input.parseFilesystem()
        val directories = directoryStructure.getDirectoryList()

        var totalSize = 0

        directories.forEach {
            if (it.getDirectorySize() <= 100000) {
                totalSize += it.getDirectorySize()
            }
        }

        return totalSize
    }

    fun part2(input: List<String>): Int {
        val directoryStructure = input.parseFilesystem()
        val directories = directoryStructure.getDirectoryList()

        val spaceNeeded = 30000000 - (70000000 - directoryStructure.getDirectorySize())

        val directorySizes = directories.map { it.getDirectorySize() }.filter { it >= spaceNeeded }

        return directorySizes.min()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

data class Directory(val name: String, var directories: MutableList<Directory> = mutableListOf(), var files: MutableList<File> = mutableListOf())
data class File(val name: String, val size: Int)