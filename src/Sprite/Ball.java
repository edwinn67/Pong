package Sprite;
import Physics.BouncingDynamic;
import Physics.CollidableObject;
import Physics.Controllable;

import java.awt.*;

public class Ball extends CollidableObject
        implements Controllable, BouncingDynamic {
    // properties
    private int radius;
    private int dx = 12;
    private int dy = 6;


    // constructors
    public Ball() {
        super();
    }
    public Ball(int radius) {
        this();
        this.width = radius*2;
        this.height = radius*2;

        this.radius = radius;
    }
    public Ball(int width, int height) {
        this();
        this.width = width;
        this.height = height;
    }

    // getters and setters
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }

    // override methods
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255,255, 255));
        g.fillArc(x, y, width, height, 0, 360);
    }
    public void draw(Graphics g, Color c) {
        g.setColor(c);
        g.fillArc(x, y, width, height, 0, 360);
    }

    @Override
    public void moveUp() {
        this.setY(this.getY() -dy);
    }
    @Override
    public void moveDown() {
        this.setY(this.getY() +dy);
    }
    @Override
    public void moveLeft() {
        this.setX(this.getX() -dx);
    }
    @Override
    public void moveRight() {
        this.setX(this.getX() +dx);
    }

    @Override
    public void moveX() {
        this.setX(this.getX() +dx);
    }
    @Override
    public void moveY() {
        this.setY(this.getY() +dy);
    }

    // methods
    public void changeDx() {
        dx = -dx;
    }
    public void changeDy() {
        dy = -dy;
    }

    public void move() {
        this.setLocation(this.getX() +dx,
                this.getY() +dy);
    }
}
