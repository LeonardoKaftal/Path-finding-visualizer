import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel


class AppPanel: JPanel() {
    private val WIDTH = 800;
    private val HEIGHT = 800;

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        this.isFocusable = true
    }

    override fun paintComponent (g: Graphics) {
        super.paintComponent(g);
        draw(g);
    }

    private fun draw(g: Graphics) {
        g.color = Color.RED
        g.fillRect(0,0,50,50)
    }


}