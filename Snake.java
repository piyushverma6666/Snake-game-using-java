public class Snake {
    final int[] x;
    final int[] y;
    int bodyParts;
    char direction; // U: Up, D: Down, L: Left, R: Right

    public Snake(int maxUnits) {
        this.x = new int[maxUnits]; // x coordinates of snake parts
        this.y = new int[maxUnits]; // y coordinates of snake parts
        this.bodyParts = 6; // Starting size of the snake
        this.direction = 'R'; // Initial direction: Right
    }

    public void move() {
        // Move each part of the snake to the position of the previous part
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Move the head in the current direction
        switch (direction) {
            case 'U':
                y[0] = y[0] - GamePanel.UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + GamePanel.UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - GamePanel.UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + GamePanel.UNIT_SIZE;
                break;
        }
    }

    public void grow() {
        bodyParts++; // Increase the body length of the snake
    }

    public boolean hasCollided() {
        // Check if the snake has collided with itself
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                return true;
            }
        }

        // Check if the snake has collided with the walls
        return x[0] < 0 || x[0] >= GamePanel.SCREEN_WIDTH || y[0] < 0 || y[0] >= GamePanel.SCREEN_HEIGHT;
    }
}
