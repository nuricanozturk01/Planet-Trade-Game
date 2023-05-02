package project.gameengine;



import project.gameengine.base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * Created by dindar.oz on 5.05.2017.
 */
public class TurnBasedGameEngine implements GameEngine {

    Game game;
    GameRenderer renderer;
    private final long TimeOut=50;
    List<Player> players = new ArrayList<>();
    int turn=0;

    public TurnBasedGameEngine(Game game, GameRenderer renderer) {
        this.game = game;
        this.renderer= renderer;
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void playARound()
    {
        initRound();
        while (!game.isOver())
        {
            playATurn();
        }
    }

    private void playATurn() {
        Player p = currentPlayer();
        Optional<Action> action = getPlayerAction(p,game);
        System.out.println(action);

        action.ifPresent(game::update);
        renderer.render(game);
        turn++;

    }

    private Player currentPlayer() {
        return players.get(turn%players.size());
    }

    private Optional<Action> getPlayerAction(Player p, Game game) {
        Action result = null;
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Action> future = executor.submit(()->p.play(game.getContext()));
        try {
            result = future.get(TimeOut, TimeUnit.SECONDS);

            return Optional.of(result);
        } catch (TimeoutException ex) {
            System.out.println(ex);
            // handle the timeout
        } catch (InterruptedException e) {
            System.out.println(e);
            // handle the interrupts
        } catch (ExecutionException e) {
            System.out.println(e);
        }
        finally {
            future.cancel(true);
        }
        return Optional.empty();
    }


    private void initRound() {
        if (players.size()<game.minimumPlayerCount())
            throw new NotEnoughPlayersException();
        if (players.size()>game.maximumPlayerCount())
            throw new TooManyPlayersException();
        turn =0;
        game.init(players);
    }
}
