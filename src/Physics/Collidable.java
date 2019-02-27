package Physics;

public interface Collidable {
    /*int getBottom();
    int getTop();
    int getLeft();
    int getRight();
    int getCenterX();
    int getCenterY();*/

    boolean isCollidingWith(CollidableObject obj);
    boolean isCollidingAtTopWith(CollidableObject obj);
    boolean isCollidingAtBottomWith(CollidableObject obj);
    boolean isCollidingAtLeftWith(CollidableObject obj);
    boolean isCollidingAtRightWith(CollidableObject obj);
}
