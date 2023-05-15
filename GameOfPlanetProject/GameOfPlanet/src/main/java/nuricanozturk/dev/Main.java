package nuricanozturk.dev;

import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.BasicConsolRenderer;
import project.gameengine.TurnBasedGameEngine;

import static nuricanozturk.dev.util.Constants.PLAYER_INITIAL_MONEY;
import static nuricanozturk.dev.util.Util.LOGGER;

public class Main
{
    private final static int TURN_COUNT = 5;

    public static void main(String[] args)
    {
        LOGGER.log("\n[Planet Trade Game Logs about creating objects and game informations]\n");
        var player1 = new PlayerImpl("Nuri Can", PLAYER_INITIAL_MONEY);
        var player2 = new PlayerImpl("John", PLAYER_INITIAL_MONEY);
        var player3 = new PlayerImpl("Anastasia", PLAYER_INITIAL_MONEY);

        var game = new PlanetTradeGame(TURN_COUNT);
        var gameEngine = new TurnBasedGameEngine(game, new BasicConsolRenderer());

        gameEngine.addPlayer(player1);
        gameEngine.addPlayer(player2);
        gameEngine.addPlayer(player3);

        gameEngine.playARound();
    }
}