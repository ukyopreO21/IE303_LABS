import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Assignment_2 {
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

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Image backgroundImage;
    private Bird bird;

    public GamePanel() {
        setPreferredSize(new Dimension(FlappyBird.WIDTH, FlappyBird.HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        try {
            backgroundImage = ImageIO.read(new File("assets/flappybirdbg.png"));
            Image birdImage = ImageIO.read(new File("assets/flappybird.png"));

            bird = new Bird(50, 300, 34, 24, birdImage);
        } catch (java.io.IOException e) {
            System.out.println("Not found images.");
        }

        Timer gameLoop = new Timer(20, this);
        gameLoop.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT, null);
        }

        if (bird != null) {
            bird.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            bird.flap();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}

class Bird {
    int x, y, width, height;
    int velocity = 0;
    int gravity = 1;
    Image img;

    public Bird(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void move() {
        velocity += gravity;
        y += velocity;

        if (y < 0) y = 0;
        if (y > FlappyBird.HEIGHT) y = FlappyBird.HEIGHT;
    }

    public void flap() {
        velocity = -10;
    }

    public void draw(Graphics g) {
        if (img != null) {
            g.drawImage(img, x, y, width, height, null);
        }
    }
}