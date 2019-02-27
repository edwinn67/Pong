package Sprite;
import Physics.CollidableObject;

import java.awt.*;

public class Paddle extends CollidableObject {

    // properties
    private int dy = 10;

    // constructors
    public Paddle() {
        super();
        this.width = 20;
        this.height = 120;
    }
    public Paddle(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    // getters and setters
    @Override
    public int getDy() {
        return dy;
    }
    @Override
    public void setDy(int dy) {
        this.dy = dy;
    }
    @Override
    public int getDx() {
        return 0;
    }
    @Override
    public void setDx(int dx) {

    }

    // override methods
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255,255,255));
        g.fillRect(x, y, width, height);
    }


}
