package gamestate;

import java.awt.*;
import java.util.LinkedList;

public interface GameState {

    void draw(Graphics g);
    void update();

    void onKeyTyped(int keyCode);
    void onKeyPressed(int keyCode);
    void onKeyReleased(int keyCode);
    void handleKeys(LinkedList<Integer> pressedKeys);



}
