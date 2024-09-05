import java.util.Random;

public class Food {
    int x;
    int y;
    Random random;

    public Food() {
        random = new Random();
        generateNewPosition();
    }

    public void generateNewPosition() {
        x = random.nextInt((int) (GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
        y = random.nextInt((int) (GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
    }
}
