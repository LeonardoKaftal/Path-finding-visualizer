import kotlin.math.abs
import kotlin.math.min

class Node(
    val x: Int,
    val y: Int,
    val isWalkable: Boolean,
    var nodeType: NodeType,
) {
    var fCost = 0;
    fun calculateFCost(targetNode: Node) {
        fCost =  calculateHCost(targetNode)
    }


    private fun calculateHCost(targetNode: Node): Int {
        val horizontalDistance = abs(x - targetNode.x)
        val verticalDistance = abs(y - targetNode.y)

        val diagonalValue = 14
        val verticalValue = 10

        return diagonalValue * min(horizontalDistance, verticalDistance) + verticalValue * abs(horizontalDistance - verticalDistance)
    }


}
