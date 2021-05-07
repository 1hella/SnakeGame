package test.java.com.wanhella.snakegame;

import main.java.com.wanhella.snakegame.Direction;
import main.java.com.wanhella.snakegame.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeTest {
    @Test
    public void constructor_default_stateExpected() {
        Snake s = new Snake();
        assertEquals(new Point(1, Snake.DEFAULT_LENGTH - 1), s.getPosition());
        assertEquals(Direction.EAST, s.getDirection());
    }

    @Test
    public void constructor_paramaterized_stateExpected() {
        Snake s = new Snake(10, 20, Direction.WEST);
        assertEquals(new Point(10 + Snake.DEFAULT_LENGTH, 20), s.getPosition());
        assertEquals(Direction.WEST, s.getDirection());
    }
}
