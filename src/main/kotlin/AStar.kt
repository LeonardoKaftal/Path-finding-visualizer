import java.util.HashSet
import java.util.PriorityQueue

class AStar (
    private val grid : Array<Array<Node>>,
    private val startingNodeCordinate:  Array<Int>,
    private val targetNodeCordinate: Array<Int>,
    private val NODE_SIZE: Int,
) {
    private val openNode: PriorityQueue<Node> = PriorityQueue(grid[0].size * grid.size,compareBy { it.fCost })
    private val closedNode: HashSet<Node> = HashSet()

    fun getBestPath(): HashSet<Node> {
        val startNode = grid[startingNodeCordinate[0]][startingNodeCordinate[1]]
        val targetNode = grid[targetNodeCordinate[0]][targetNodeCordinate[1]]
        openNode.add(startNode)

        while (!openNode.isEmpty()) {
            val currentNode = openNode.remove()
            closedNode.add(currentNode)

            if (currentNode == targetNode) break

            for (neighbour in getAdjacentNodes(currentNode)) {
                if (!neighbour.isWalkable || closedNode.contains(neighbour)) continue

                if (!openNode.contains(neighbour)) {
                    neighbour.calculateFCost(targetNode)
                    openNode.add(neighbour)
                }
            }
        }

        return closedNode
    }


    fun getAdjacentNodes(node: Node): List<Node> {
        val adjacentNodes = mutableListOf<Node>()
        val gridX = node.x / NODE_SIZE
        //  important to notice that NODE_SIZE is subtracted by 5 because five is the GAP used in the drawing phase, the GAP variable in AppPannel
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