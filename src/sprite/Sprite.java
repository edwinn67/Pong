package sprite;

import java.awt.*;

public abstract class Sprite {

    // properties
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    // setters and getters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // public methods
    public void setLocation(int x, int y) {this.x = x; this.y = y;}
    public int getTop() {
        return this.getY();
    }
    public int getBottom() {
        return this.getY()
                +this.getHeight();
    }
    public int getLeft() {
        return this.getX();
    }
    public int getRight() {
        return this.getX()
                +this.getWidth();
    }
    public int getCenterX() {
        return this.getX()
                +this.getWidth() /2;
    }
    public int getCenterY() {
        return this.getY()
                +this.getHeight() /2;
    }

    // abstract methods
    public abstract void draw(Graphics g);

}
