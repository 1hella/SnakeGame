package main.java.com.wanhella.snakegame;

import java.awt.*;

public class Snake {
    public static final int DEFAULT_LENGTH = 10;
    private int length = DEFAULT_LENGTH;
    private Point[] positions; // length - 1 is the head, 0 is the tail
    private Direction direction;
    private boolean isGrowing = false;


    public Snake() {
        this.direction = Direction.EAST;
        positions = new Point[length];
        for (int i = length - 1; i >= 0; i--) {
            positions[i] = new Point(i, 1);
        }
    }

    public Snake(int x, int y, Direction direction) {
        this.direction = direction;
        this.positions = new Point[length];

        if (direction == Direction.NORTH) {
            for (int i = y; i < y + length; i++) {
                positions[length - 1 - (i - y)] = new Point(x, i);
            }
        } else if (direction == Direction.SOUTH) {
            for (int i = y - length + 1; i <= y; i++) {
                positions[i - y + length - 1] = new Point(x, i);
            }
        } else if (direction == Direction.EAST) {
            for (int i = x - length + 1; i <= x; i++) {
                positions[length - 1 - (x - i)] = new Point(i, y);
            }
        } else if (direction == Direction.WEST) {
            for (int i = x + length - 1; i >= x; i--) {
                positions[length - 1 - (i - x)] = new Point(i, y);
            }
        }
    }

    public void turn(Direction direction) {
        switch (direction) {
            case EAST:
                if (this.direction != Direction.WEST) {
                    this.direction = direction;
                }
                break;
            case WEST:
                if (this.direction != Direction.EAST) {
                    this.direction = direction;
                }
                break;
            case NORTH:
                if (this.direction != Direction.SOUTH) {
                    this.direction = direction;
                }
                break;
            case SOUTH:
                if (this.direction != Direction.NORTH) {
                    this.direction = direction;
                }
        }
    }

    public void move() {
        Point head = positions[length - 1];
        if (isGrowing) {
            length++;
            isGrowing = false;
        } else {
            System.arraycopy(positions, 1, positions, 0, length - 1);
        }
        Point newPoint = new Point();
        switch (direction) {
            case NORTH:
                newPoint = new Point(head.x, head.y - 1);
                break;
            case SOUTH:
                newPoint = new Point(head.x, head.y + 1);
                break;
            case EAST:
                newPoint = new Point(head.x + 1, head.y);
                break;
            case WEST:
                newPoint = new Point(head.x - 1, head.y);
                break;
        }
        positions[length - 1] = newPoint;
    }

    public Point getPosition() {
        return positions[length - 1];
    }

    public void grow() {
        this.isGrowing = true;
        if (length + 1 > positions.length) {
            Point[] newPositions = new Point[positions.length * 2];
            System.arraycopy(positions, 0, newPositions, 0, length);
            positions = newPositions;
        }
    }

    public boolean isTouchingSelf() {
        Point head = positions[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            if (head.equals(positions[i])) {
                return true;
            }
        }

        return false;
    }

    public boolean isAtPosition(Point point) {
        for (Point p : positions) {
            if (p != null && p.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }
}
