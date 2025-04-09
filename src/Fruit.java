import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fruit {
    private int x;
    private int y;
    private final ImageIcon img;

    public Fruit() {
        img = new ImageIcon("src/fruit.png");
        this.x = (int) (Math.floor(Math.random() * Main.column) * Main.CELL_SIZE);
        this.y = (int) (Math.floor(Math.random() * Main.row) * Main.CELL_SIZE);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void drawFruit(Graphics g) {
        img.paintIcon(null, g, this.x, this.y);
        // g.setColor(Color.GREEN);
        // g.fillOval(this.x, this.y, Main.CELL_SIZE, Main.CELL_SIZE);
    }

    public void setNewLocation(Snake s) {
        int newX;
        int newY;
        boolean overLapping;
        do {
            newX = (int) (Math.floor(Math.random() * Main.column) * Main.CELL_SIZE);
            newY = (int) (Math.floor(Math.random() * Main.row) * Main.CELL_SIZE);
            overLapping = checkOverLap(newX, newY, s);
        } while (overLapping);
        this.x = newX;
        this.y = newY;
    }

    private boolean checkOverLap(int x, int y, Snake s) {
        ArrayList<Node> snakeBody = s.getSnakeBody();

        for (Node body : snakeBody) {
            if (x == body.x && y == body.y) {
                return true;
            }
        }
        return false;
    }

}
