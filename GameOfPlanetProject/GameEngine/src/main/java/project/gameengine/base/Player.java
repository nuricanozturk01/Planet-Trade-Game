package project.gameengine.base;

/**
 * Created by dindar.oz on 5.05.2017.
 */
public interface Player {

    String getName();
    Action play(GameContext context);
    void prepareForGame(GameContext context);
}
