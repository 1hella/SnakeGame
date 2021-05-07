package main.java.com.wanhella.snakegame;

import java.awt.*;

public class Fruit {
    private Point position;

    public Fruit() {
        this.position = new Point();
    }

    public Fruit(Point position) {
        this.position = position;
    }

    public Fruit(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
