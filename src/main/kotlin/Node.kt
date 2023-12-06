
class Node(
    val x: Int,
    val y: Int,
    var isWalkable: Boolean,
    var nodeType: NodeType,
) {
    var gCost = 0
    var hCost = 0
    var parent: Node? = null

    fun getFCost(): Int {
        return gCost + hCost
    }


}
