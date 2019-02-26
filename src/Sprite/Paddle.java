package Sprite;
import Physics.CollidableObject;

import java.awt.*;

public class Paddle extends CollidableObject {

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

    // override methods
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255,255,255));
        g.fillRect(x, y, width, height);
    }
}
