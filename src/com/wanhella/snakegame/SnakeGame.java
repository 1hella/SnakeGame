package com.wanhella.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame {
    private static final int NUM_ROWS = 50;
    private static final int NUM_COLS = 50;
    private static final int DELAY_MILLIS = 200;
    GameArea gameArea;

    public SnakeGame() {
        this.gameArea = new GameArea(NUM_ROWS, NUM_COLS);
    }

    public static void main(String[] args) {
        JFrame frame = makeJFrame();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            snakeGame.drawGame(frame);
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
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                JLabel label = new JLabel("    ");
                if (gameArea.getSnake().isAtPosition(new Point(i, j))) {
                    label.setBackground((Color.green));
                } else if (gameArea.getFruit() != null && gameArea.getFruit().getPosition().equals(new Point(i, j))) {
                    label.setBackground(Color.red);
                } else {
                    label.setBackground(Color.BLACK);
                }
                label.setOpaque(true);
                frame.add(label);
            }
        }
        frame.pack();
        frame.repaint();
    }

    private void gamePlayLoop() {
        if (!gameArea.hasFruit()) {
            gameArea.generateFruit();
        }
        if (gameArea.isSnakeTouchingFruit()) {
            gameArea.clearFruit();
            gameArea.getSnake().grow();
        }
        gameArea.getSnake().move();
    }

    private boolean isGameOver() {
        return gameArea.isSnakeTouchingWall() || gameArea.isSnakeTouchingItself();
    }

    public static JFrame makeJFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        return frame;
    }
}
