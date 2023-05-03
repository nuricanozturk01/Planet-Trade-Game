package nuricanozturk.dev;


import nuricanozturk.dev.entity.Player;
import project.gameengine.BasicConsolRenderer;
import project.gameengine.TurnBasedGameEngine;

public class Main
{
    public static void main(String[] args)
    {
        var player1 = new Player("Nuri Can");
        var player2 = new Player("John");

        var game = new GameOfPlanet();
        var gameEngine = new TurnBasedGameEngine(game, new BasicConsolRenderer());

        gameEngine.addPlayer(player1);
        gameEngine.addPlayer(player2);

        gameEngine.playARound();
    }
}