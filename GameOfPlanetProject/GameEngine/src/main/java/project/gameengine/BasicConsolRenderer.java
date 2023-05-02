package project.gameengine;

import project.gameengine.base.Game;
import project.gameengine.base.GameRenderer;

public class BasicConsolRenderer implements GameRenderer {
    @Override
    public void render(Game game) {
        System.out.println(game);
    }
}
