package test.java.com.wanhella.snakegame;

import main.java.com.wanhella.snakegame.Direction;
import main.java.com.wanhella.snakegame.GameArea;
import main.java.com.wanhella.snakegame.Snake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameAreaTest {
    private GameArea gameArea;
    private static final int NUM_ROWS = 30;
    private static final int NUM_COLS = 40;

    @BeforeEach
    public void setup() {
        this.gameArea = new GameArea(NUM_ROWS, NUM_COLS);
    }

    @Test
    public void constructor_parameterized_stateExpected() {
        assertEquals(NUM_ROWS, gameArea.getNumRows());
        assertEquals(NUM_COLS, gameArea.getNumCols());
    }

    @Test
    public void generateFruit_random_inBounds() {
        assertTrue(gameArea.hasFruit());

        Point position = gameArea.getFruit().getPosition();
        assertTrue(position.x > 0);
        assertTrue(position.x < NUM_COLS - 1);
        assertTrue(position.y > 0);
        assertTrue(position.y < NUM_ROWS - 1);
    }

    @Test
    public void isSnakeTouchingWall_north_true() {
        gameArea.placeSnake(NUM_COLS / 2, 1, Direction.NORTH);
        assertFalse(gameArea.isSnakeTouchingWall());
        gameArea.getSnake().move();
        assertTrue(gameArea.isSnakeTouchingWall());
    }

    @Test
    public void isSnakeTouchingWall_south_true() {
        gameArea.placeSnake(NUM_COLS / 2, NUM_ROWS-2, Direction.SOUTH);
        assertFalse(gameArea.isSnakeTouchingWall());
        gameArea.getSnake().move();
        assertTrue(gameArea.isSnakeTouchingWall());
    }

    @Test
    public void isSnakeTouchingWall_east_true() {
        gameArea.placeSnake(NUM_COLS - 2, NUM_ROWS / 2, Direction.EAST);
        assertFalse(gameArea.isSnakeTouchingWall());
        gameArea.getSnake().move();
        assertTrue(gameArea.isSnakeTouchingWall());
    }

    @Test
    public void isSnakeTouchingWall_west_true() {
        gameArea.placeSnake(1, NUM_ROWS / 2, Direction.WEST);
        assertFalse(gameArea.isSnakeTouchingWall());
        gameArea.getSnake().move();
        assertTrue(gameArea.isSnakeTouchingWall());
    }

    @Test
    public void clearFruit_default_stateExpected() {
        gameArea.clearFruit();
        assertFalse(gameArea.hasFruit());
    }

    @Test
    public void isSnakeTouchingFruit_generated_true() {
        gameArea.generateFruit(1, 1);
        gameArea.placeSnake(2, 1, Direction.WEST);
        assertFalse(gameArea.isSnakeTouchingFruit());
        gameArea.getSnake().move();
        assertTrue(gameArea.isSnakeTouchingFruit());
    }

    @Test
    public void generateFruit_outOfBounds_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameArea.generateFruit(0, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameArea.generateFruit(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameArea.generateFruit(NUM_COLS - 1, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameArea.generateFruit(0, NUM_ROWS - 1));
    }

    @Test
    public void isSnakeTouchingSelf_default_true() {
        Snake snake = gameArea.getSnake();
        snake.turn(Direction.NORTH);
        snake.move();
        snake.turn(Direction.WEST);
        snake.move();
        snake.turn(Direction.SOUTH);
        assertFalse(gameArea.isSnakeTouchingSelf());
        snake.move();
        assertTrue(gameArea.isSnakeTouchingSelf());
    }

    @Test
    public void isSnakeTouchingSelf_parameterized_true() {
        gameArea.placeSnake(NUM_COLS, NUM_ROWS, Direction.NORTH);
        Snake snake = gameArea.getSnake();
        snake.turn(Direction.EAST);
        snake.move();
        snake.turn(Direction.SOUTH);
        snake.move();
        snake.turn(Direction.WEST);
        assertFalse(gameArea.isSnakeTouchingSelf());
        snake.move();
        assertTrue(gameArea.isSnakeTouchingSelf());
    }
}
