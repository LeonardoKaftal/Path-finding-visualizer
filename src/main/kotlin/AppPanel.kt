import java.awt.Dimension
import java.awt.Graphics
import java.util.ArrayList
import javax.swing.JButton
import javax.swing.JPanel


class AppPanel: JPanel() {
    private val WIDTH = 800;
    private val HEIGHT = 800;
    private val NODE_PER_ROW = 20;
    private val NODE_SIZE = WIDTH / NODE_PER_ROW
    private val TOTAL_ROW = 18;
    private val TOTAL_NUMBER_OF_NODE = TOTAL_ROW * NODE_PER_ROW - 1
    private val nodeList = ArrayList<Node>()
    private val buttonsList = listOf(JButton("Clear"), JButton("Start"))

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        this.isFocusable = true
        appendButton()
        generateGrid()
    }

    private fun appendButton() {
        buttonsList.forEach { button -> add(button) }
    }

    private fun generateGrid() {
        var column = 0;
        var row = 0;

        for (i in 0..TOTAL_NUMBER_OF_NODE) {
            nodeList.add(Node(column * NODE_SIZE, row * NODE_SIZE + NODE_SIZE * 2))
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
        nodeList.forEach { node -> g.drawRect(node.x, node.y,node.size,node.size) }
    }


}