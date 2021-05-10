package main.java.com.wanhella.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;

public class SnakeGame {
    private static final int NUM_ROWS = 50;
    private static final int NUM_COLS = 50;
    private static final int DELAY_MILLIS = 200;
    private final GameArea gameArea;
    private int score = 0;

    public SnakeGame() {
        this.gameArea = new GameArea(NUM_ROWS, NUM_COLS);
    }

    public static void main(String[] args) {
        JFrame frame = makeJFrame();

        SnakeGame snakeGame = new SnakeGame();
        snakeGame.drawGame(frame);
        frame.setFocusable(true);
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                snakeGame.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                snakeGame.keyReleased(e);
            }
        };
        frame.addKeyListener(listener);
        frame.setVisible(true);
        while (!snakeGame.isGameOver()) {
            snakeGame.gamePlayLoop();
            try {
                SwingUtilities.invokeAndWait(() -> snakeGame.drawGame(frame));
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(DELAY_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void keyReleased(KeyEvent e) {

    }

    private void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gameArea.getSnake().turn(Direction.WEST);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameArea.getSnake().turn(Direction.EAST);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            gameArea.getSnake().turn(Direction.NORTH);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gameArea.getSnake().turn(Direction.SOUTH);
        }
    }

    private void drawGame(JFrame frame) {
        frame.getContentPane().removeAll();
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        frame.add(gamePanel, BorderLayout.CENTER);
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                JLabel label = new JLabel("    ");
                if (gameArea.getSnake().isAtPosition(new Point(j, i))) {
                    label.setBackground((Color.green));
                } else if (gameArea.getFruit() != null && gameArea.getFruit().getPosition().equals(new Point(j, i))) {
                    label.setBackground(Color.red);
                } else {
                    label.setBackground(Color.BLACK);
                }
                label.setOpaque(true);
                gamePanel.add(label);
            }
        }
        JLabel scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(scoreLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.repaint();
    }

    private void gamePlayLoop() {
        if (!gameArea.hasFruit()) {
            gameArea.generateFruit();
        }
        if (gameArea.isSnakeTouchingFruit()) {
            score++;
            gameArea.clearFruit();
            gameArea.getSnake().grow();
        }
        gameArea.getSnake().move();
    }

    private boolean isGameOver() {
        return gameArea.isSnakeTouchingWall() || gameArea.isSnakeTouchingSelf();
    }

    private static JFrame makeJFrame() {
        JFrame frame = new JFrame("Snake Game");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        return frame;
    }
}
