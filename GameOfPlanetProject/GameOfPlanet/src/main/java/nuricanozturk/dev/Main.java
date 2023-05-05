package nuricanozturk.dev;


import nuricanozturk.dev.entity.BlackHole;
import nuricanozturk.dev.entity.Player;
import nuricanozturk.dev.factory.SpaceshipFactory;
import project.gameengine.BasicConsolRenderer;
import project.gameengine.TurnBasedGameEngine;

public class Main
{
    public static void main(String[] args)
    {
        var player1 = new Player("Nuri Can");
        var player2 = new Player("John");

        /*var game = new GameOfPlanet();
        var gameEngine = new TurnBasedGameEngine(game, new BasicConsolRenderer());

        gameEngine.addPlayer(player1);
        gameEngine.addPlayer(player2);

        gameEngine.playARound();*/

        var b = new BlackHole("B");
        var g = b.explode();
        g.getPlanets().forEach(p -> System.out.println(p.getName()));
        g.getPlanets().forEach(m -> System.out.println(m.getMarket().getName()));
        g.getPlanets().forEach(gg -> {
            gg.getMarket().getCommodities().forEach(System.out::println);
            System.out.println();
        });
        var ss = SpaceshipFactory.createSpaceships();
        ss.forEach(s -> System.out.println(s));


    }
}