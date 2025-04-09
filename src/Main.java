import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JPanel implements KeyListener {

    public static final int CELL_SIZE = 20;
    public static int width = 400;
    public static int height = 400;
    public static int row = height / CELL_SIZE;
    public static int column = width / CELL_SIZE;
    private Snake snake;
    private Fruit fruit;
    private Timer t;
    private final int speed = 100;
    private static String direction;
    private boolean allowKeyPress;
    private int score;

    public Main() {
        snake = new Snake();
        fruit = new Fruit();
        setTimer();
        direction = "Right";
        addKeyListener(this);
        allowKeyPress = true;
    }

    private void setTimer() {
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, speed);
    }

    private void reset() {
        score = 0;
        if (snake != null) {
            snake.getSnakeBody().clear();
        }
        allowKeyPress = true;
        direction = "Right";
        snake = new Snake();
        fruit = new Fruit();
        setTimer();
    }


    @Override
    public void paintComponent(Graphics g) {
        // Check if snake bites itself
        ArrayList<Node> snakeBody = snake.getSnakeBody();
        Node head = snakeBody.getFirst();
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeBody.get(i).x == head.x && snakeBody.get(i).y == head.y) {
                allowKeyPress = false;
                t.cancel();
                t.purge();
                int response = JOptionPane.showOptionDialog(this, "GameOver!! Would you like to start over?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, JOptionPane.YES_OPTION);
                switch (response) {
                    case JOptionPane.CLOSED_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.YES_OPTION:
                        reset();
                        return;
                }
            }

        }


        g.fillRect(0, 0, width, height);
        fruit.drawFruit(g);
        snake.drawSnake(g);

        // Remove snake tail and put it in head
        int snakeX = snake.getSnakeBody().get(0).x;
        int snakeY = snake.getSnakeBody().get(0).y;
        // right, x+= CELL_SIZE
        // left, x-= CELL_SIZE
        // down, y+= CELL_SIZE
        // up, x-= CELL_SIZE
        if (direction.equals("Left")) {
            snakeX -= CELL_SIZE;
        } else if (direction.equals("Up")) {
            snakeY -= CELL_SIZE;
        } else if (direction.equals("Right")) {
            snakeX += CELL_SIZE;
        } else if (direction.equals("Down")) {
            snakeY += CELL_SIZE;
        }


        Node newHead = new Node(snakeX, snakeY);

        // Check if the snake eats the fruit
        if (snake.getSnakeBody().getFirst().x == fruit.getX() && snake.getSnakeBody().getFirst().y == fruit.getY()) {
            // 1. Set fruit to a new location
            fruit.setNewLocation(snake);
            // 2. draw fruit
            fruit.drawFruit(g);
            // 3. score++

        } else {
            snake.getSnakeBody().removeLast();
        }


        snake.getSnakeBody().addFirst(newHead);
        allowKeyPress = true;
        requestFocusInWindow();

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (allowKeyPress) {
            int keyCode = e.getKeyCode();
            if (keyCode == 37 && !direction.equals("Right")) {
                direction = "Left";
            } else if (keyCode == 38 && !direction.equals("Down")) {
                direction = "Up";
            } else if (keyCode == 39 && !direction.equals("Left")) {
                direction = "Right";
            } else if (keyCode == 40 && !direction.equals("Up")) {
                direction = "Down";
            }
            allowKeyPress = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}