import kotlin.math.abs
import kotlin.math.min

class Node(
    val x: Int,
    val y: Int,
    val isWalkable: Boolean,
    var nodeType: NodeType
) {

    fun getFCost(startingNode: Node, targetNode: Node): Int {
        return calculateGCost(startingNode) + calculateHCost(targetNode)
    }

    private fun calculateGCost(startingNode: Node): Int {
        val horizontalDistance = abs(x - startingNode.x)
        val verticalDistance = abs(y - startingNode.y)

        val diagonalValue = 14
        val verticalValue = 10
        return diagonalValue * min(horizontalDistance, verticalDistance) + verticalValue * abs(horizontalDistance - verticalDistance)
    }

    private fun calculateHCost(targetNode: Node): Int {
        val horizontalDistance = abs(x - targetNode.x)
        val verticalDistance = abs(y - targetNode.y)

        val diagonalValue = 14
        val verticalValue = 10

        return diagonalValue * min(horizontalDistance, verticalDistance) + verticalValue * abs(horizontalDistance - verticalDistance)
    }


}
