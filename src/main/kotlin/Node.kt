import kotlin.math.abs
import kotlin.math.sqrt

class Node(
    val x: Int,
    val y: Int,
    var isWalkable: Boolean,
    var nodeType: NodeType,
) {
    var parent: Node? = null
    var gCost = 0
    var hCost = 0
    var fCost = 0

    fun calculateFCost(targetNode: Node) {
        gCost = calculateGCost(targetNode)
        hCost = calculateHCost(targetNode)
        fCost = gCost + hCost
    }

    fun calculateGCost(targetNode: Node): Int {
        val dx = abs(targetNode.x - x)
        val dy = abs(targetNode.y - y)
        return (10 * sqrt((dx * dx + dy * dy).toDouble())).toInt()
    }

    private fun calculateHCost(targetNode: Node): Int {
        val dx = abs(targetNode.x - x)
        val dy = abs(targetNode.y - y)
        return (10 * sqrt((dx * dx + dy * dy).toDouble())).toInt()
    }
}