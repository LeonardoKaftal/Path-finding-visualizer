import kotlin.math.abs
import kotlin.math.min

class Node(
    val x: Int,
    val y: Int,
    val isWalkable: Boolean,
    var nodeType: NodeType
) {
     var gCost: Int = 0
     var hCost: Int = 0

    fun getFCost(startingNode: Node, targetNode: Node): Int {
        return calculateGCost(startingNode) + calculateHCost(targetNode)
    }

    private fun calculateGCost(parentNode: Node): Int {
       return parentNode.gCost + calculateMoveCost(parentNode)
    }

    private fun calculateHCost(targetNode: Node): Int {
        val horizontalDistance = abs(x - targetNode.x)
        val verticalDistance = abs(y - targetNode.y)

        val diagonalValue = 14
        val verticalValue = 10

        return diagonalValue * min(horizontalDistance, verticalDistance) + verticalValue * abs(horizontalDistance - verticalDistance)
    }



    private fun calculateMoveCost(otherNode: Node): Int {
        return if (x != otherNode.x && y != otherNode.y) {
            14
        } else {
            10
        }
    }

}
