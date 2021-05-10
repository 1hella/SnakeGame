package test.java.com.wanhella.snakegame;

import main.java.com.wanhella.snakegame.Fruit;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FruitTest {
    @Test
    public void constructor_default_stateExpected() {
        Fruit f = new Fruit();
        assertEquals(new Point(1,1), f.getPosition());
    }

    @Test
    public void constructor_parameterized_stateExpected() {
        Fruit f = new Fruit(10, 10);
        assertEquals(new Point(10, 10), f.getPosition());
    }

    @Test
    public void constructor_singleArgument_stateExpected() {
        Fruit f = new Fruit(new Point(10,10));
        assertEquals(new Point(10, 10), f.getPosition());
    }
}
