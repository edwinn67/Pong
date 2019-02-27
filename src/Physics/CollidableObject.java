package Physics;

import Sprite.Sprite;

public abstract class CollidableObject
        extends Sprite
        implements Collidable {


    @Override
    public boolean isCollidingWith(CollidableObject obj) {
        return ((this.getBottom() > obj.getTop() && this.getTop() < obj.getBottom())
                && (this.getRight() > obj.getLeft() && this.getLeft() < obj.getRight()));
    }
    @Override
    public boolean isCollidingAtTopWith(CollidableObject obj) {
        return this.isCollidingWith(obj) &&
                (this.getTop() +this.getDy() < obj.getBottom()
                && this.getBottom() +this.getDy() > obj.getBottom());
    }
    @Override
    public boolean isCollidingAtBottomWith(CollidableObject obj) {
        return this.isCollidingWith(obj) &&
                (this.getBottom() +this.getDy() > obj.getTop()
                && this.getTop() +this.getDy() < obj.getTop());
    }
    @Override
    public boolean isCollidingAtLeftWith(CollidableObject obj) {
        return this.isCollidingWith(obj) &&
                (this.getLeft() +this.getDx() < obj.getRight()
                        && this.getRight() +this.getDx() > obj.getRight());
    }
    @Override
    public boolean isCollidingAtRightWith(CollidableObject obj) {
        return this.isCollidingWith(obj) &&
                (this.getRight() +this.getDx() > obj.getLeft()
                        && this.getLeft() +this.getDx() < obj.getLeft());
    }


    public abstract int getDx();
    public abstract void setDx(int dx);
    public abstract int getDy();
    public abstract void setDy(int dy);
}
