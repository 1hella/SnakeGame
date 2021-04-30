package com.wanhella.snakegame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SnakeGame {
    private static final int NUM_ROWS = 50;
    private static final int NUM_COLS = 50;
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
        frame.setVisible(true);
        while (!snakeGame.isGameOver()) {
            snakeGame.gamePlayLoop();
            snakeGame.drawGame(frame);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawGame(JFrame frame) {
        frame.getContentPane().removeAll();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                JLabel label = new JLabel("asdf");
                if (gameArea.getFruit().getPosition().equals(new Point(i, j))) {
                    label.setBackground(Color.red);
                } else if (gameArea.getSnake().isAtPosition(new Point(i, j))) {
                    label.setBackground((Color.green));
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
//        if (directionalInput != null) {
//            gameArea.getSnake().turn(directionalInput);
//        }
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
