import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Assignment_1 {
    static void main() {
        FlappyBird.startGame();
    }
}

class FlappyBird {
    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;

    public static void startGame() {
        JFrame frame = new JFrame("Flappy Bird");

        try {
            Image appIcon = ImageIO.read(new File("assets/flappybird.png"));
            frame.setIconImage(appIcon);
        } catch (java.io.IOException e) {
            System.out.println("Icon file not found.");
        }

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class GamePanel extends JPanel {
    private Image backgroundImage;

    public GamePanel() {
        setPreferredSize(new Dimension(FlappyBird.WIDTH, FlappyBird.HEIGHT));

        try {
            backgroundImage = ImageIO.read(new File("assets/flappybirdbg.png"));
        } catch (java.io.IOException e) {
            System.out.println("Background image not found.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT, null);
        }

    }
}