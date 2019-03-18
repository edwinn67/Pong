package util;

import gamestate.GameState;


public class GameStatesManager {

    // properties
    private static GameState actualGameState;

    public static void setGameState(GameState state) {
        actualGameState = state;
    }
    public static GameState getGameState() {
        return actualGameState;
    }
}
