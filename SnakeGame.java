import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        this.add(new GamePanel()); // Add the game panel
        this.setTitle("Snake Game"); // Set the title of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setResizable(false); // Prevent resizing
        this.pack(); // Fit window around components
        this.setVisible(true); // Make the window visible
        this.setLocationRelativeTo(null); // Center the window
    }

    public static void main(String[] args) {
        new SnakeGame(); // Create and run the game
    }
}
