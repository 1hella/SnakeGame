package test.java.com.wanhella.snakegame;

import main.java.com.wanhella.snakegame.Direction;
import main.java.com.wanhella.snakegame.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    @Test
    public void constructor_default_stateExpected() {
        Snake s = new Snake();
        assertEquals(new Point(Snake.DEFAULT_LENGTH - 1, 1), s.getPosition());
        assertEquals(Direction.EAST, s.getDirection());
        assertEquals(Snake.DEFAULT_LENGTH, s.getLength());
    }

    @Test
    public void constructor_parameterized_stateExpected() {
        Snake s = new Snake(20, 10, Direction.WEST);
        assertEquals(new Point(20, 10), s.getPosition());
        assertEquals(Direction.WEST, s.getDirection());
        assertEquals(Snake.DEFAULT_LENGTH, s.getLength());
    }

    @Test
    public void move_default_stateExpected() {
        Snake s = new Snake();
        s.move();
        assertEquals(new Point(Snake.DEFAULT_LENGTH, 1), s.getPosition());
        assertEquals(Snake.DEFAULT_LENGTH, s.getLength());
    }

    @Test
    public void grow_default_stateExpected() {
        Snake s = new Snake();
        s.grow();
        assertEquals(Snake.DEFAULT_LENGTH, s.getLength());
        assertEquals(new Point(Snake.DEFAULT_LENGTH - 1, 1), s.getPosition());
        s.move();
        assertEquals(new Point(Snake.DEFAULT_LENGTH, 1), s.getPosition());
        assertEquals(Snake.DEFAULT_LENGTH + 1, s.getLength());
    }

    @Test
    public void turn_south_stateExpected() {
        Snake s = new Snake();
        s.turn(Direction.SOUTH);
        assertEquals(new Point(Snake.DEFAULT_LENGTH - 1, 1), s.getPosition());
        s.move();
        assertEquals(new Point( Snake.DEFAULT_LENGTH - 1, 2), s.getPosition());
        assertEquals(Snake.DEFAULT_LENGTH, s.getLength());
    }

    @Test
    public void turn_turnOpposite_noChange() {
        Snake s = new Snake();
        s.turn(Direction.WEST);
        assertEquals(Direction.EAST, s.getDirection());
    }

    @Test
    public void isAtPosition_default_true() {
        Snake s = new Snake();
        assertTrue(s.isAtPosition(new Point(1, 1)));
    }

    @Test
    public void isAtPosition_default_false() {
        Snake s = new Snake();
        assertFalse(s.isAtPosition(new Point(2, 2)));
    }
}
