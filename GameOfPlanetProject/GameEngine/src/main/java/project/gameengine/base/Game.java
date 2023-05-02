package project.gameengine.base;



import java.util.List;

/**
 * Created by dindar.oz
 */
public interface Game {
    boolean isOver();

    void init(List<Player> players);
    GameContext getContext();
    void update(Action action);



    int minimumPlayerCount();
    int maximumPlayerCount();

}
