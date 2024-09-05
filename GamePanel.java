import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 150;

    Snake snake;
    Food food;
    boolean running = false;
    Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        snake = new Snake(GAME_UNITS); // Initialize the snake
        food = new Food(); // Initialize the food
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Optional grid lines for visual aid
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            // Draw the food
            g.setColor(Color.red);
            g.fillOval(food.x, food.y, UNIT_SIZE, UNIT_SIZE);

            // Draw the snake
            for (int i = 0; i < snake.bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green); // Snake head
                    g.fillRect(snake.x[i], snake.y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0)); // Snake body
                    g.fillRect(snake.x[i], snake.y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // Display the score
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + (snake.bodyParts - 6), (SCREEN_WIDTH - metrics.stringWidth("Score: " + (snake.bodyParts - 6))) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void checkFoodCollision() {
        if ((snake.x[0] == food.x) && (snake.y[0] == food.y)) {
            snake.grow();
            food.generateNewPosition();
        }
    }

    public void checkCollisions() {
        if (snake.hasCollided()) {
            running = false;
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkFoodCollision();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.direction != 'R') {
                        snake.direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.direction != 'L') {
                        snake.direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.direction != 'D') {
                        snake.direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.direction != 'U') {
                        snake.direction = 'D';
                    }
                    break;
            }
        }
    }
}
