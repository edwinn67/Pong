package gamestate;

import gamestate.Menu.OptionsMenu;
import physics.CollidableObject;
import Sprite.Ball;
import Sprite.Paddle;
import Util.AudioFilePlayer;
import Util.FontManager;
import Util.GameStatesManager;
import Util.WindowManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Random;


import static javax.sound.sampled.AudioSystem.getAudioInputStream;


public class World implements GameState {

    // properties
    private final Rectangle bounds;
    private PlayerDirection player1Direction;
    private PlayerDirection player2Direction;
    private boolean twoPlayers;
    private int maxBallDy;
    private int maxScore;

    // enums
    private enum PlayerDirection {
        STAND,
        UP,
        DOWN
    }

    // sprites
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;

    // constructor
    public World(boolean twoPlayers) {
        this.bounds = WindowManager.getBounds();
        this.twoPlayers = twoPlayers;

        initProps();
        reset();
    }
    private void initProps() {
        maxBallDy = 8;
        maxScore = 10;
    }

    // init world
    private void initSprites() {
        // sprite's instantiation
        paddle1 = new Paddle(15, 110);
        paddle2 = new Paddle(15, 110);
        ball = new Ball(15);

        // init paddles
        initPaddles();

        // init ball
        initBall();
    }
    private void initPaddles() {
        // scope variables
        int margin = 50;

        // init paddle1
        paddle1.setX(bounds.width -margin -paddle1.getWidth());
        paddle1.setY(bounds.height /2 -paddle1.getHeight());
        paddle1.setDy(4);

        // init paddle2
        paddle2.setX(bounds.x +margin);
        paddle2.setY(bounds.height /2 -paddle2.getHeight());

        initPaddle2Speed();
    }
    private void initBall() {
        ball.setX(bounds.width /2 -ball.getRadius());
        ball.setY(bounds.height /2 -ball.getRadius());
        ball.setDx(-4);
        ball.setDy(getRandomNumber(2,4));
    }
    private void initPaddle2Speed() {
        if (!twoPlayers) {
            if (OptionsMenu.getDifficulty() == 0) {
                paddle2.setDy(getRandomNumber(2,3));
            }
            else if (OptionsMenu.getDifficulty() == 1) {
                paddle2.setDy(getRandomNumber(4,6));
            }
            else if (OptionsMenu.getDifficulty() == 2) {
                paddle2.setDy(getRandomNumber(7,8));
            }
        }
    }


    // game over and reset
    private void reset() {
        initSprites();
        resetScore();
    }
    private void resetScore() {
        paddle1.setScore(0);
        paddle2.setScore(0);
    }
    private boolean isGameOver() {
        return ((paddle1.getScore() >= maxScore)
                || (paddle2.getScore() >= maxScore));
    }

    // draw world
    @Override
    public void draw(Graphics g) {
        drawBackground(g);
        drawSprites(g);
        drawField(g);
        drawScore(g);
    }
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    private void drawSprites(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
    }
    private void drawField(Graphics g) {
        int n = 31;
        for (int i=0; i < n; i++) {
            int width = 4;
            int height = 12;
            int space = 20;
            int start = (bounds.height
                    -((height*n) + ((space-height)*n)) ) /2;

            g.setColor(new Color(255, 255, 255));
            g.fillRect(bounds.width / 2 - width / 2, start +i*space, width, height);
        }
    }
    private void drawScore(Graphics g) {
        int size = 80;

        Font font = FontManager.getFont(size);
        FontMetrics metrics = g.getFontMetrics(font);

        g.setFont(font);

        g.drawString(Integer.toString(paddle1.getScore()),
            bounds.width/2 +bounds.width/4 -size/2,
                bounds.y +(metrics.getHeight()/2 + metrics.getAscent()));

        g.drawString(Integer.toString(paddle2.getScore()),
            bounds.width/4 -size/2,
                bounds.y +(metrics.getHeight()/2 + metrics.getAscent()));
    }

    // update world
    @Override
    public void update() {
        updatePlayer1();
        updatePlayer2();
        updateBall();
        updateScore();
        updateState();

//        play("src/Sounds/beep1.ogg");

    }
    private void updatePlayer1() {
        if (player1Direction == (PlayerDirection.UP)
                && !isCollidingAtTop(paddle1)) {
            paddle1.moveUp();
        } else
        if (player1Direction == (PlayerDirection.DOWN)
                && !isCollidingAtBottom(paddle1)) {
            paddle1.moveDown();
        }
    }
    private void updatePlayer2() {
        if (twoPlayers) {
            updatePlayer2Player();
        }
        else {
            updatePlayer2Ai();
        }
    }
    private void updatePlayer2Player() {
        if (player2Direction == (PlayerDirection.UP)
                && !isCollidingAtTop(paddle2)) {
            paddle2.moveUp();
        } else
        if (player2Direction == (PlayerDirection.DOWN)
                && !isCollidingAtBottom(paddle2)) {
            paddle2.moveDown();
        }
    }
    private void updatePlayer2Ai() {
        if ((paddle2.getTop() > ball.getTop())
                && !isCollidingAtTop(paddle2))
            paddle2.moveUp();
        else
        if ((paddle2.getBottom() < ball.getBottom())
                && !isCollidingAtBottom(paddle2))
            paddle2.moveDown();
        else
        if (ball.getDy() == 0 && paddle2.isCollidingWith(ball)) {
            if (paddle2.getDy() < 0 && !isCollidingAtTop(paddle2))
                paddle2.moveUp();
            else
                paddle2.moveDown();
        }
    }
    private void updateBall() {
        handleBallCollision();
        ball.move();
    }
    private void updateScore() {
        if (ball.getLeft() < bounds.x) {
            paddle1.setScore(paddle1.getScore() +1);
            AudioFilePlayer.playSound("src/sound/score.wav");
            initBall();
        } else
        if (ball.getRight() > bounds.width) {
            paddle2.setScore(paddle2.getScore() +1);
            AudioFilePlayer.playSound("src/sound/score.wav");
            initBall();
        }
    }
    private void updateState() {
        if (isGameOver()) {
            GameStatesManager.setGameState(new GameOver());
        }
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
            AudioFilePlayer.playSound("src/sound/beep2.wav");
        }
        handleBlocking();
    }
    private void handlePaddlesCollision() {
        handleCollisionWith(paddle1);
        handleCollisionWith(paddle2);
    }
    private void handleCollisionWith(Paddle paddle) {
        if (ball.isCollidingWith(paddle)) {
            int yDiff =
                    ball.getCenterY()
                            -paddle.getCenterY();

            ball.setDy(yDiff / maxBallDy);
            ball.changeDx();
            AudioFilePlayer.playSound("src/sound/beep1.wav");
        }
    }
    private void handleBlocking() {
        if (ball.getTop() < bounds.y) {
            ball.setY(bounds.y);
            ball.setX(ball.getX() -paddle1.getWidth());
        } else if (ball.getBottom() > bounds.height) {
            ball.setY(bounds.height -ball.getRadius()*2);
            ball.setX(ball.getX() -paddle1.getWidth());
        }
    }

    // bounds collision methods
    private boolean isCollidingAtTop(CollidableObject object) {
        return object.getTop()
                +object.getDy() < bounds.y;
    }
    private boolean isCollidingAtBottom(CollidableObject object) {
        return object.getBottom()
                +object.getDy() > bounds.height;
    }

    // handle keys
    @Override
    public void onKeyTyped(int keyCode) {

    }
    @Override
    public void onKeyPressed(int keyCode) {


    }
    @Override
    public void onKeyReleased(int keyCode) {

    }
    @Override
    public void handleKeys(LinkedList<Integer> pressedKeys) {
        handlePlayer1Keys(pressedKeys);
        handlePlayer2Keys(pressedKeys);
    }
    private void handlePlayer1Keys(LinkedList<Integer> pressedKeys) {
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            player1Direction = PlayerDirection.UP;
        } else if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            player1Direction = PlayerDirection.DOWN;
        }
        else {
            player1Direction = PlayerDirection.STAND;
        }
    }
    private void handlePlayer2Keys(LinkedList<Integer> pressedKeys) {
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            player2Direction = PlayerDirection.UP;
        } else if (pressedKeys.contains(KeyEvent.VK_S)) {
            player2Direction = PlayerDirection.DOWN;
        }
        else {
            player2Direction = PlayerDirection.STAND;
        }
    }



    private int getRandomNumber(int min, int max) {
        long seed = LocalDateTime.now().getNano();
        return min +(new Random(seed)).nextInt() % max;
    }


}
