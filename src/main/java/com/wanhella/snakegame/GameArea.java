package main.java.com.wanhella.snakegame;

import java.awt.*;

public class GameArea {
    private Fruit fruit;
    private Snake snake;
    private final int NUM_ROWS;
    private final int NUM_COLS;

    public GameArea(int numRows, int numCols) {
        this.NUM_ROWS = numRows;
        this.NUM_COLS = numCols;
        this.generateFruit();
        this.placeSnake();
    }

    public void generateFruit() {
        int x = (int) (Math.random() * NUM_COLS - 2) + 1;
        int y = (int) (Math.random() * NUM_ROWS - 2) + 1;
        this.fruit = new Fruit(x, y);
    }

    public void generateFruit(int x, int y) {
        if (x < 1 || x > NUM_COLS - 2 || y < 1 || y > NUM_ROWS - 2) {
            throw new IllegalArgumentException();
        }
        this.fruit = new Fruit(x,y);
    }

    private void placeSnake() {
        this.snake = new Snake();
    }

    public void placeSnake(int x, int y, Direction direction) {
        this.snake = new Snake(x, y, direction);
    }

    public boolean isSnakeTouchingWall() {
        Point snakePosition = snake.getPosition();
        return snakePosition.x == 0 || snakePosition.x == NUM_COLS - 1
                || snakePosition.y == 0 || snakePosition.y == NUM_ROWS - 1;
    }

    public boolean hasFruit() {
        return fruit != null;
    }

    public Snake getSnake() {
        return snake;
    }

    public void clearFruit() {
        fruit = null;
    }

    public boolean isSnakeTouchingFruit() {
        Point snakePosition = snake.getPosition();
        return fruit != null && snakePosition.equals(fruit.getPosition());
    }

    public Fruit getFruit() {
        return fruit;
    }

    public boolean isSnakeTouchingSelf() {
        return snake.isTouchingSelf();
    }

    public int getNumRows() {
        return NUM_ROWS;
    }

    public int getNumCols() {
        return NUM_COLS;
    }
}
