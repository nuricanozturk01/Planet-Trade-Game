package nuricanozturk.dev;


import nuricanozturk.dev.entity.Player;
import project.gameengine.BasicConsolRenderer;
import project.gameengine.TurnBasedGameEngine;

import static nuricanozturk.dev.util.Constants.PLAYER_INITIAL_MONEY;

public class Main
{
    private final static int TURN_COUNT = 5;

    public static void main(String[] args)
    {
        var player1 = new Player("Nuri Can", PLAYER_INITIAL_MONEY);
        var player2 = new Player("John", PLAYER_INITIAL_MONEY);
        var player3 = new Player("Anastasia", PLAYER_INITIAL_MONEY);

        var game = new GameOfPlanet(TURN_COUNT);
        var gameEngine = new TurnBasedGameEngine(game, new BasicConsolRenderer());

        gameEngine.addPlayer(player1);
        gameEngine.addPlayer(player2);
        gameEngine.addPlayer(player3);

        gameEngine.playARound();
    }
}