import java.io.File

private const val FILENAME = "2022/07/input.txt"

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val file = File(FILENAME).readLines()

    val rootDirectory = Node("\\", Type.DIRECTORY)
    var currentNode = rootDirectory

    for (line in file) {
        if (line == "$ cd /" || line == "$ ls") {
            continue
        } else if (line.startsWith("$ cd ..")) {
            currentNode = currentNode.getParent()
        } else if (line.startsWith("$ cd")) {
            val name = line.split(" ").last()
            currentNode = currentNode.getChildren(name)
        } else if (line.startsWith("dir")) {
            val name = line.split(" ").last()
            currentNode.addChildren(Node(name, Type.DIRECTORY))
        } else {
            val (size, name) = line.split(" ")
            currentNode.addChildren(Node(name, Type.FILE, size.toLong()))
        }
    }

    println(rootDirectory.findDirectories().map { it.totalSize() }.filter { it <= 100_000 }.sum())
}

private fun partTwo() {
    val file = File(FILENAME).readLines()

    val rootDirectory = Node("\\", Type.DIRECTORY)
    var currentNode = rootDirectory

    for (line in file) {
        if (line == "$ cd /" || line == "$ ls") {
            continue
        } else if (line.startsWith("$ cd ..")) {
            currentNode = currentNode.getParent()
        } else if (line.startsWith("$ cd")) {
            val name = line.split(" ").last()
            currentNode = currentNode.getChildren(name)
        } else if (line.startsWith("dir")) {
            val name = line.split(" ").last()
            currentNode.addChildren(Node(name, Type.DIRECTORY))
        } else {
            val (size, name) = line.split(" ")
            currentNode.addChildren(Node(name, Type.FILE, size.toLong()))
        }
    }

    val filesystemSize = 70_000_000
    val requiredFreeSpace = 30_000_000
    val neededToFree = requiredFreeSpace - (filesystemSize - rootDirectory.totalSize())

    val result = rootDirectory.findDirectories().map { Node(it.name, it.type, it.totalSize()) }.sortedBy { it.size }
        .find { it.size > neededToFree }

    println(result!!.size)
}

class Node(
    val name: String,
    val type: Type,
    val size: Long = 0,
    private val parent: Node? = null,
    private val children: MutableList<Node> = mutableListOf()
) {
    fun addChildren(node: Node): Node {
        val modified = Node(node.name, node.type, node.size, this)
        children.add(modified)
        return modified
    }

    fun getParent(): Node {
        return parent ?: throw IllegalStateException("No parent")
    }

    fun getChildren(name: String): Node {
        return children.find { it.name == name } ?: throw IllegalStateException("No children with that name")
    }

    fun findDirectories(dirs: MutableList<Node> = mutableListOf()): List<Node> {
        if (type == Type.DIRECTORY) {
            dirs.add(this)
        }

        if (children.isEmpty()) {
            return dirs
        } else {
            children.forEach { it.findDirectories(dirs) }
        }

        return dirs
    }

    fun totalSize(): Long {
        return if (children.isEmpty()) {
            size
        } else {
            children.sumOf { it.totalSize() }
        }
    }
}

enum class Type {
    DIRECTORY,
    FILE
}
