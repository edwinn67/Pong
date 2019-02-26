package Objects;

import java.awt.*;

public interface Collidable {
    boolean isCollidingWith(CollidableObject obj);
    int getBottom();
    int getTop();
    int getLeft();
    int getRight();
    int getCenterX();
    int getCenterY();

//    void updateCollisionBox(CollisionBox r);
}
