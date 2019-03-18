package physics;

public interface Collidable {
    boolean isCollidingWith(CollidableObject obj);
    boolean isCollidingAtTopWith(CollidableObject obj);
    boolean isCollidingAtBottomWith(CollidableObject obj);
    boolean isCollidingAtLeftWith(CollidableObject obj);
    boolean isCollidingAtRightWith(CollidableObject obj);
}
