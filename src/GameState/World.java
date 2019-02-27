package GameState;

import Physics.CollidableObject;
import Physics.PlayerDirection;
import Sprite.Ball;
import Sprite.Paddle;
import Sprite.Sprite;

import java.awt.*;

public class World {

    // properties
    private Rectangle bounds;
    private PlayerDirection playerDirection;

    // getters and setters
    public PlayerDirection getPlayerDirection() {
        return playerDirection;
    }
    public void setPlayerDirection(PlayerDirection playerDirection) {
        this.playerDirection = playerDirection;
    }

    // sprites
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;

    public World(Rectangle bounds) {
        this.bounds = bounds;

        paddle1 = new Paddle();
        paddle2 = new Paddle();
        ball = new Ball(20);

        initSprites();
    }

    private void initSprites() {
        // init paddle1
        paddle1.setX(bounds.width -50);
        paddle1.setY(bounds.height /2 -paddle1.getHeight());

        // init paddle2
        paddle2.setX(bounds.x +50);
        paddle2.setY(bounds.height /2 -paddle2.getHeight());

        // init ball
        ball.setX(bounds.width /2 -ball.getRadius()*2);
        ball.setY(bounds.height /2 -ball.getRadius()*2);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
    }

    public void update() {
        updateBall();
        updatePlayer1();
        updatePlayer2();
    }
    private void updatePlayer1() {
        if (playerDirection == (PlayerDirection.DOWN)
                && !isCollidingAtBottom(paddle1)) {
            paddle1.setY(paddle1.getY() +paddle1.getDy());
        } else if (playerDirection == (PlayerDirection.UP)
                && !isCollidingAtTop(paddle1)) {
            paddle1.setY(paddle1.getY() -paddle1.getDy());
        }
    }
    private void updatePlayer2() {
        if (!(paddle2.getTop() -paddle2.getHeight()/2 < ball.getTop()
                && paddle2.getBottom() -paddle2.getHeight()/2 > ball.getBottom())) {
            if (ball.getDy() < 0 && !isCollidingAtTop(paddle2))
                paddle2.setY(paddle2.getY() -11);
            else if (!isCollidingAtBottom(paddle2))
                paddle2.setY(paddle2.getY() +11);
        }

    }
    private void updateBall() {
        handleBallCollision();
        handleBlocking();
        ball.move();
    }

    // physics handlers
    private void handleBallCollision() {
        // handling ball collisions with walls
        handleBoundsCollision();
        // handling ball collisions with paddles
        handlePaddlesCollision();
    }
    private void handleBoundsCollision() {
        if (isCollidingAtTop(ball)
                || isCollidingAtBottom(ball)) {
            ball.changeDy();
        }



        // DEBUG
        else if (ball.getLeft() +ball.getDx() < bounds.x
                || ball.getRight() +ball.getDx() > bounds.width) {
            ball.changeDx();
        }
    }
    private void handlePaddlesCollision() {
        handleCollisionWith(paddle1);
        handleCollisionWith(paddle2);
    }
    private void handleCollisionWith(Paddle paddle) {
        handleTopBottomCollision(paddle);
        handleLeftRightCollision(paddle);
    }
    private void handleTopBottomCollision(Paddle paddle) {
        if (ball.isCollidingAtTopWith(paddle)) {
            if (ball.getDy() < 0) {
                ball.changeDy();
            }
        } else if (ball.isCollidingAtBottomWith(paddle)) {
            if (ball.getDy() > 0) {
                ball.changeDy();
            }
        }
    }
    private void handleLeftRightCollision(Paddle paddle) {
        if (ball.isCollidingAtLeftWith(paddle)) {
            if (ball.getDx() < 0) {
                ball.changeDx();
            }
        } else if (ball.isCollidingAtRightWith(paddle)) {
            if (ball.getDx() > 0) {
                ball.changeDx();
            }
        }
    }
    private void handleBlocking() {
        if (ball.getCenterY() < bounds.y) {
            ball.setY(bounds.y);
            ball.setX(ball.getX() -paddle1.getWidth());
        } else if (ball.getCenterY() > bounds.height) {
            ball.setY(bounds.height -ball.getRadius()*2);
            ball.setX(ball.getX() -paddle1.getWidth());
        }
    }

    // boolean methods
    private boolean isCollidingAtTop(CollidableObject object) {
        return object.getTop()
                +object.getDy() < bounds.y;
    }
    private boolean isCollidingAtBottom(CollidableObject object) {
        return object.getBottom()
                +object.getDy() > bounds.height;
    }


    // DEBUG
    public void debug(Graphics g) {
        Ball point = new Ball(2);
        point.setX(bounds.width /2);
        point.setY(bounds.height /2);
        point.draw(g, new Color(0, 255, 252));
    }





    private void handlePaddlesCollision(Paddle paddle) {
        if (ball.isCollidingWith(paddle)) {
            /*if (ball.getTop() + ball.getDy() > paddle.getTop()
                    && ball.getBottom() + ball.getDy() < paddle.getBottom()) {
                ball.changeDy();
            } else if (ball.getRight() + ball.getDx() > paddle.getLeft()
                    && ball.getBottom() + ball.getDx() < paddle.getBottom()) {
                ball.changeDx();
            }*/
            ball.changeDx();

        }
    }
    private boolean isCollidingWithWalls() {
        return (ball.getY() < bounds.y
                || ball.getY() > bounds.height);
    }
    private boolean isCollidingWithPaddle() {
        return (ball.isCollidingWith(paddle1)
                || ball.isCollidingWith(paddle2));
    }
    private void handleMovesOf(Sprite player) {
        if (playerDirection == (PlayerDirection.DOWN)) {
            player.setY(player.getY() +10);
            ball.setDy(+6);
        }
        if (playerDirection == (PlayerDirection.UP)) {
            player.setY(player.getY() -10);
            ball.setDy(-6);
        }
        if (playerDirection == (PlayerDirection.RIGHT)) {
            player.setX(player.getX() +10);
        }
        if (playerDirection == (PlayerDirection.LEFT)) {
            player.setX(player.getX() -10);
        }
    }
}
