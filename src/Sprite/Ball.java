package Sprite;
import Physics.CollidableObject;

import java.awt.*;

public class Ball extends CollidableObject {

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
    public int getDx() {
        return dx;
    }
    public void setDx(int dx) {
        this.dx = dx;
    }
    public int getDy() {
        return dy;
    }
    public void setDy(int dy) {
        this.dy = dy;
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
