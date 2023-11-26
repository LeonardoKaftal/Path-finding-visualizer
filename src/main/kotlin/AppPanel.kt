import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.util.ArrayList
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.math.ceil


class AppPanel: JPanel(), MouseListener{
    private val WIDTH = 800;
    private val HEIGHT = 800;
    private val NODE_PER_ROW = 20;
    private val NODE_SIZE = WIDTH / NODE_PER_ROW
    private val TOTAL_ROW = 18;
    private val TOTAL_NUMBER_OF_NODE = TOTAL_ROW * NODE_PER_ROW - 1
    private val nodeList = ArrayList<Node>()
    private val buttonsList = listOf(JButton("Clear"), JButton("Start"))
    private var startNodeIndex =  ceil(Math.random() * TOTAL_NUMBER_OF_NODE).toInt()
    private var targetNodeIndex = ceil(Math.random() * TOTAL_NUMBER_OF_NODE).toInt()
    private var isChangingNodePosition = false
    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        this.isFocusable = true
        addMouseListener(this)
        appendButton()
        generateGrid()

        while (startNodeIndex == targetNodeIndex) {
            startNodeIndex = ceil(Math.random() * TOTAL_NUMBER_OF_NODE).toInt()
        }
    }

    private fun appendButton() {
        buttonsList.forEach { button -> add(button) }
    }

    private fun generateGrid() {
        var column = 0;
        var row = 0;

        for (i in 0..TOTAL_NUMBER_OF_NODE) {
            when(i) {
                startNodeIndex -> nodeList.add(Node(column * NODE_SIZE, row * NODE_SIZE + NODE_SIZE * 2, isWalkable = true ,NodeType.START))
                targetNodeIndex -> nodeList.add(Node(column * NODE_SIZE, row * NODE_SIZE + NODE_SIZE * 2, isWalkable = true, NodeType.TARGET))
                else -> nodeList.add(Node(column * NODE_SIZE, row * NODE_SIZE + NODE_SIZE * 2, isWalkable = true, NodeType.NODE))
            }
            column++
            if (column >= NODE_PER_ROW) {
                column = 0;
                row++
            }
        }
    }

    override fun paintComponent (g: Graphics) {
        super.paintComponent(g);
        draw(g);
    }

    private fun draw(g: Graphics) {
        for (i in 0..<nodeList.size) {
            val node = nodeList[i]
            when (node.nodeType) {
                NodeType.START -> {
                    g.color = Color.GREEN
                    g.fillRect(node.x,node.y,NODE_SIZE,NODE_SIZE)
                }
                NodeType.TARGET -> {
                    g.color = Color.RED
                    g.fillRect(node.x,node.y,NODE_SIZE,NODE_SIZE)
                }
                else -> {
                    g.color = Color.BLACK
                    g.drawRect(node.x, node.y, NODE_SIZE, NODE_SIZE)
                }
            }
        }

    }

    override fun mouseClicked(e: MouseEvent) {

    }

    override fun mousePressed(e: MouseEvent) {
        val xMouseGridPos = e.x / NODE_SIZE * NODE_SIZE
        val yMouseGridPos = e.y / NODE_SIZE * NODE_SIZE

        // check if the user clicked on the target or on the start node
        if (xMouseGridPos == nodeList[targetNodeIndex].x && yMouseGridPos == nodeList[targetNodeIndex].y ||
            xMouseGridPos == nodeList[startNodeIndex].x && yMouseGridPos == nodeList[startNodeIndex].y) {
            isChangingNodePosition = true
        }
    }

    override fun mouseReleased(e: MouseEvent?) {
        isChangingNodePosition = false
    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }


}