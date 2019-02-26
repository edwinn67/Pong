package Sprite;

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
    public void setLocation(int x, int y) {this.x = x; this.y = y;}
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

    // abstract methods
    public abstract void draw(Graphics g);

}
