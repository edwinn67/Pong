package Sprite;
import physics.CollidableObject;

import java.awt.*;

public class Paddle extends CollidableObject {

    // properties
    private int dy;
    private int score;

    // constructors
    public Paddle(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    // getters and setters
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
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


    public void moveUp() {
        this.setY(this.getY() -this.getDy());
    }
    public void moveDown() {
        this.setY(this.getY() +this.getDy());
    }


}
