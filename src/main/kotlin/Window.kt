import javax.swing.JFrame

class Window: JFrame("Path finding") {
    init {
        this.add(AppPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.isVisible = true;
        this.setLocationRelativeTo(null)
    }

}