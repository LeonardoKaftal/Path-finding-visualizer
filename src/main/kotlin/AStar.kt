import kotlin.math.abs

class AStar(
    private val grid: Array<Array<Node>>,
    private val startingNodeCordinate: Array<Int>,
    private val targetNodeCordinate: Array<Int>,
    private val NODE_SIZE: Int,
) {
    private val openNode:  HashSet<Node> = HashSet()
    private val closedNode: HashSet<Node> = HashSet()

    fun getBestPath(): List<Node> {
        val startNode = grid[startingNodeCordinate[0]][startingNodeCordinate[1]]
        val targetNode = grid[targetNodeCordinate[0]][targetNodeCordinate[1]]
        openNode.add(startNode)

        while (openNode.isNotEmpty()) {
            var currentNode = openNode.first()

            openNode.forEachIndexed {index, node ->
                if (index > 0) {
                    if (node.getFCost() <= currentNode.getFCost() && node.hCost < currentNode.hCost) currentNode = node
                }
            }

            openNode.remove(currentNode)
            closedNode.add(currentNode)

            if (currentNode == targetNode) {
                return retracePath(startNode,targetNode)
            }

            for (neighbour in getAdjacentNodes(currentNode)) {
                if (!neighbour.isWalkable || closedNode.contains(neighbour)) continue

                val newMovementCostToNeighbour = currentNode.gCost + calculateDistance(currentNode,neighbour)
                if (newMovementCostToNeighbour < neighbour.gCost || !openNode.contains(neighbour)) {
                    neighbour.gCost = newMovementCostToNeighbour
                    neighbour.hCost = calculateDistance(neighbour,targetNode)
                    neighbour.parent = currentNode

                    if (!openNode.contains(neighbour)) openNode.add(neighbour)
                }
            }
        }

        println("Invalid path!")
        return ArrayList()
    }

    fun calculateDistance(nodeA: Node, nodeB : Node): Int {
        val dx = abs(nodeA.x - nodeB.x)
        val dy = abs(nodeA.y - nodeB.y)

        if (dx > dy) return 14 * dy + 10 * (dx - dy)
        return 14 * dx + 10 * (dy - dx)
    }

    fun retracePath(startNode: Node, endNode: Node): ArrayList<Node> {
        val path = ArrayList<Node>()
        var currentNode = endNode

        while (currentNode != startNode) {
            path.add(currentNode)
            currentNode = currentNode.parent!!
        }
        path.reverse()
        return path
    }

    private fun getAdjacentNodes(node: Node): List<Node> {
        val adjacentNodes = mutableListOf<Node>()
        val gridX = node.x / NODE_SIZE
        //  important to notice that NODE_SIZE is subtracted by 5 because five is the GAP to cap
        val gridY = node.y / NODE_SIZE - 5

        val possibleCoordinates = listOf(
            Pair(gridY - 1, gridX - 1),
            Pair(gridY - 1, gridX),
            Pair(gridY - 1, gridX + 1),
            Pair(gridY, gridX - 1),
            Pair(gridY, gridX + 1),
            Pair(gridY + 1, gridX - 1),
            Pair(gridY + 1, gridX),
            Pair(gridY + 1, gridX + 1)
        )

        for ((x, y) in possibleCoordinates) {
            if (x in 0..<grid.size && y in 0..<grid[0].size) {
                adjacentNodes.add(grid[x][y])
            }
        }

        return adjacentNodes
    }
}
