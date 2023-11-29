import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.random.Random

class AppPanel : JPanel(), MouseListener, MouseMotionListener {
    private val WIDTH = 900
    private val HEIGHT = 900
    private val NODE_PER_ROW = 45
    private val NODE_SIZE = WIDTH / NODE_PER_ROW
    private val TOTAL_ROW = 40
    private val GAP = NODE_SIZE * 5
    private val grid = Array(TOTAL_ROW) { Array<Node?>(NODE_PER_ROW) { null } }
    private val buttonsList = arrayOf(JButton("Clear"), JButton("Start"))
    private var startNodeIndexCordinate: Array<Int> = arrayOf(Random.nextInt(TOTAL_ROW), Random.nextInt(NODE_PER_ROW))
    private var targetNodeIndexCordinate: Array<Int> = arrayOf(Random.nextInt(TOTAL_ROW), Random.nextInt(NODE_PER_ROW))
    private var isChangingStartNodePosition: Boolean = false
    private var isChangingTargetNodePosition: Boolean = false

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        this.isFocusable = true
        addMouseListener(this)
        addMouseMotionListener(this)
        appendButton()
        generateGrid()

        while (startNodeIndexCordinate[0] == targetNodeIndexCordinate[0] && startNodeIndexCordinate[1] == targetNodeIndexCordinate[1]) {
            startNodeIndexCordinate = arrayOf(Random.nextInt(TOTAL_ROW), Random.nextInt(NODE_PER_ROW))
        }
    }

    private fun appendButton() {
        buttonsList.forEach { button -> add(button) }
    }

    private fun generateGrid() {
        for (row in 0..<TOTAL_ROW) {
            for (column in 0..<NODE_PER_ROW) {
                val nodeType = when {
                    row == startNodeIndexCordinate[0] && column == startNodeIndexCordinate[1] -> NodeType.START
                    row == targetNodeIndexCordinate[0] && column == targetNodeIndexCordinate[1] -> NodeType.TARGET
                    else -> NodeType.NODE
                }

                grid[row][column] = Node(column * NODE_SIZE, row * NODE_SIZE + GAP, isWalkable = true, nodeType)
            }
        }
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        for (row in 0..<TOTAL_ROW) {
            for (column in 0..<NODE_PER_ROW) {
                val node = grid[row][column]
                when (node?.nodeType) {
                    NodeType.START -> {
                        g.color = Color.GREEN
                        g.fillRect(node.x, node.y, NODE_SIZE, NODE_SIZE)
                    }
                    NodeType.TARGET -> {
                        g.color = Color.RED
                        g.fillRect(node.x, node.y, NODE_SIZE, NODE_SIZE)
                    }
                    else -> {
                        g.color = Color.BLACK
                        g.drawRect(node!!.x, node.y, NODE_SIZE, NODE_SIZE)
                    }
                }
            }
        }
    }

    override fun mouseClicked(e: MouseEvent) {
        // implement as needed
    }

    override fun mousePressed(e: MouseEvent) {
        val xMouseGridPos = e.x / NODE_SIZE
        val yMouseGridPos = (e.y - GAP) / NODE_SIZE

        if (xMouseGridPos == targetNodeIndexCordinate[1] && yMouseGridPos == targetNodeIndexCordinate[0]) {
            isChangingTargetNodePosition = true
        } else if (xMouseGridPos == startNodeIndexCordinate[1] && yMouseGridPos == startNodeIndexCordinate[0])  {
            isChangingStartNodePosition = true
        }
    }


    override fun mouseReleased(e: MouseEvent?) {
        isChangingTargetNodePosition = false
        isChangingStartNodePosition = false
    }

    override fun mouseEntered(e: MouseEvent?) {
        // implement as needed
    }

    override fun mouseExited(e: MouseEvent?) {
        // implement as needed
    }

    override fun mouseDragged(e: MouseEvent) {
        val xMouseGridPos = e.x / NODE_SIZE
        val yMouseGridPos = (e.y - GAP) / NODE_SIZE

        if (xMouseGridPos < NODE_PER_ROW && yMouseGridPos < TOTAL_ROW && xMouseGridPos >= 0 && yMouseGridPos >= 0) {
            when {
                isChangingTargetNodePosition -> {
                    if (xMouseGridPos != startNodeIndexCordinate[1] || yMouseGridPos != startNodeIndexCordinate[0]) {
                        val prevTargetNode = grid[targetNodeIndexCordinate[0]][targetNodeIndexCordinate[1]]
                        prevTargetNode!!.nodeType = NodeType.NODE

                        targetNodeIndexCordinate[1] = xMouseGridPos
                        targetNodeIndexCordinate[0] = yMouseGridPos

                        grid[yMouseGridPos][xMouseGridPos]!!.nodeType = NodeType.TARGET
                        repaint()
                    }
                }
                isChangingStartNodePosition -> {
                    if (xMouseGridPos != targetNodeIndexCordinate[1] || yMouseGridPos != targetNodeIndexCordinate[0]) {
                        val prevStartNode = grid[startNodeIndexCordinate[0]][startNodeIndexCordinate[1]]
                        prevStartNode!!.nodeType = NodeType.NODE

                        startNodeIndexCordinate[1] = xMouseGridPos
                        startNodeIndexCordinate[0] = yMouseGridPos

                        grid[yMouseGridPos][xMouseGridPos]!!.nodeType = NodeType.START
                        repaint()
                    }
                }
            }
        }
    }



    override fun mouseMoved(e: MouseEvent?) {
    }
}
