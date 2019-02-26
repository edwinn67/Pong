package GameState;

import Physics.PlayerDirection;
import Sprite.Ball;
import Sprite.Paddle;

import java.awt.*;

public class World {

    // properties
    private Rectangle bounds;
    private PlayerDirection playerDirection;

    // getters and setters
    public Rectangle getBounds() {
        return bounds;
    }
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
        initSprites();
    }
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

    public World() {
        paddle1 = new Paddle();
        paddle2 = new Paddle();
        ball = new Ball(20);
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

//        paddle1.draw(g);
//        paddle2.draw(g);
        ball.draw(g);
    }

    public void update() {
        updatePlayer1();
//        handleMovesOf(ball);
        updateBall();
        updatePlayer2();
    }
    private void updatePlayer1() {
        if (playerDirection == (PlayerDirection.DOWN)) {
            paddle1.setY(paddle1.getY() +10);
        }
        if (playerDirection == (PlayerDirection.UP)) {
            paddle1.setY(paddle1.getY() -10);
        }
    }
    private void updatePlayer2() {
        if (paddle2.getCenterY() < ball.getCenterY())
            paddle2.setY(paddle2.getY() +10);
        else
        if (paddle2.getCenterY() > ball.getCenterY())
            paddle2.setY(paddle2.getY() -10);
    }
    private void updateBall() {
        handleBallCollision();
        ball.move();
    }
    private boolean handleBoundsCollisions() {
        return (ball.getY() < bounds.y
                || ball.getY() > bounds.height);
    }
    private void handleBoundsCollisions(int i) {
        if (ball.getTop() < bounds.y || ball.getBottom() > bounds.height) {
            ball.changeDy();
        }
        else if (ball.getLeft() < bounds.x || ball.getRight() > bounds.width) {
            ball.changeDx();
        }
    }
    private boolean isCollidingWithPaddle() {
        return (ball.isCollidingWith(paddle1)
                || ball.isCollidingWith(paddle2));
    }
    private void handleBallCollision() {
        /*if (handleBoundsCollisions()
                || isCollidingWithPaddle()) {
            ball.changeDx();
        }*/
        handleBoundsCollisions(0);
    }



    //    private void handleMovesOf(Sprite player) {
//        if (playerDirection == (PlayerDirection.DOWN)) {
//            player.setY(player.getY() +1);
//        }
//        if (playerDirection == (PlayerDirection.UP)) {
//            player.setY(player.getY() -1);
//        }
//        if (playerDirection == (PlayerDirection.RIGHT)) {
//            player.setX(player.getX() +1);
//        }
//        if (playerDirection == (PlayerDirection.LEFT)) {
//            player.setX(player.getX() -1);
//        }
//    }


    // DEBUG
    public void debug(Graphics g) {
        Ball point = new Ball(2);
        point.setX(bounds.width /2);
        point.setY(bounds.height /2);
        point.draw(g, new Color(0, 255, 252));
    }
}
