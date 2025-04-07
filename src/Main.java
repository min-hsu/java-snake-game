
import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    public static final int CELL_SIZE = 20;
    public static int width = 400;
    public static int height = 400;
    public static int row = height / CELL_SIZE;
    public static int col = width / CELL_SIZE;
    private Snake snake;

    public Main() {
        snake = new Snake();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, width, height);
        snake.drawSnake(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Snack game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

    }
}