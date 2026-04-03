import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class Assignment_3 {
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
    private Image topPipeImage, bottomPipeImage;
    private Background background;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    int groundHeight = 64;

    public GamePanel() {
        setPreferredSize(new Dimension(FlappyBird.WIDTH, FlappyBird.HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        try {
            Image backgroundImage = ImageIO.read(new File("assets/flappybirdbg.png"));
            Image birdImage = ImageIO.read(new File("assets/flappybird.png"));
            topPipeImage = ImageIO.read(new File("assets/toppipe.png"));
            bottomPipeImage = ImageIO.read(new File("assets/bottompipe.png"));

            background = new Background(backgroundImage);
            bird = new Bird(50, 300, 34, 24, birdImage);
            pipes = new ArrayList<>();
        } catch (java.io.IOException e) {
            System.out.println("Not found images.");
        }


        Timer gameLoop = new Timer(20, this);
        gameLoop.start();

        Timer pipeTimer = new Timer(1500, e -> placePipes());
        pipeTimer.start();
    }

    private void placePipes() {
        int pipeWidth = 64;
        int pipeHeight = 512;
        int openingSpace = 110 + (int) (Math.random() * 41);

        int playableHeight = FlappyBird.HEIGHT - groundHeight;
        int minPipeY = -pipeHeight + 100;
        int maxPipeY = -pipeHeight + playableHeight - openingSpace - 100;

        int randomPipeY = (int) (minPipeY + Math.random() * (maxPipeY - minPipeY));
        Pipe topPipe = new Pipe(FlappyBird.WIDTH, randomPipeY, pipeWidth, pipeHeight, topPipeImage);
        pipes.add(topPipe);
        Pipe bottomPipe = new Pipe(FlappyBird.WIDTH, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight, bottomPipeImage);
        pipes.add(bottomPipe);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            background.draw(g);
        }

        for (Pipe p: pipes) {{
            p.draw(g);
        }}

        if (background != null) {
            background.drawGround(g, groundHeight);
        }

        if (bird != null) {
            bird.draw(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();

        for (int i = 0; i < pipes.size(); i++) {
            Pipe p = pipes.get(i);
            p.move(4);

            if (p.x + p.width < 0) {
                pipes.remove(i);
                i--;
            }
        }

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

class Background {
    Image img;

    public Background(Image img) {
        this.img = img;
    }

    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT, null);
    }

    public void drawGround(Graphics g, int groundHeight) {
        int yCoord = FlappyBird.HEIGHT - groundHeight;

        g.drawImage(img,
                0, yCoord, FlappyBird.WIDTH, FlappyBird.HEIGHT,
                0, yCoord, FlappyBird.WIDTH, FlappyBird.HEIGHT,
                null);
    }
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

class Pipe {
    int x, y, width, height;
    Image img;
    boolean passed = false;

    public Pipe(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void move(int speed) {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }
}