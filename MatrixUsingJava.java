/*
 *This is a code to create a Matrix Rain on a black background Using Java Language.
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MatrixUsingJava extends JPanel {
    private final int screenWidth = 800;
    private final int screenHeight = 600;
    private final int numColumns = 80;
    private final int fontSize = 20;
    private final int charWidth = 10;
    private final char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private final Random random = new Random();

    private final Drop[] drops = new Drop[numColumns];

    public void Matrix() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(screenWidth, screenHeight));

        for (int i = 0; i < numColumns; i++) {
            drops[i] = new Drop(i * charWidth, random.nextInt(screenHeight), random.nextInt(5) + 1);
        }

        Timer timer = new Timer(100, e -> {
            for (Drop drop : drops) {
                drop.update();
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(new Font("Monospaced", Font.BOLD, fontSize));
        g2d.setColor(Color.GREEN);

        for (Drop drop : drops) {
            g2d.drawString(Character.toString(drop.getCharacter()), drop.getX(), drop.getY());
        }
    }

    private class Drop {
        private int x;
        private int y;
        private char character;
        private int speed;

        public Drop(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.character = getRandomCharacter();
            this.speed = speed;
        }

        public void update() {
            y = (y + speed) % screenHeight;
            character = getRandomCharacter();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public char getCharacter() {
            return character;
        }

        private char getRandomCharacter() {
            return characters[random.nextInt(characters.length)];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Matrix Rain");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new MatrixUsingJava());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
