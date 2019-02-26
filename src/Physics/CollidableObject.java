package Objects;

import Sprite.Sprite;

import java.awt.*;

public abstract class CollidableObject
        extends Sprite
        implements Collidable {

    // override methods
    @Override
    public int getTop() {
        return this.getY();
    }
    @Override
    public int getBottom() {
        return this.getY()
                +this.getHeight();
    }
    @Override
    public int getLeft() {
        return this.getX();
    }
    @Override
    public int getRight() {
        return this.getX()
                +this.getWidth();
    }
    @Override
    public int getCenterX() {
        return this.getX()
                +this.getWidth() /2;
    }
    @Override
    public int getCenterY() {
        return this.getY()
                +this.getHeight() /2;
    }

    @Override
    public boolean isCollidingWith(CollidableObject obj) {
        return ((this.getBottom() > obj.getTop() && this.getTop() < obj.getBottom()))
                && (this.getRight() > obj.getLeft() && this.getLeft() < obj.getRight());
    }
}
