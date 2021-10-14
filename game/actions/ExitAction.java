package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameStatus;

/**
 * This class is responsible for letting player to exit current gamemode
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class ExitAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(GameStatus.EXITED);
        return "Player exits current game!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Exit game";
    }
}
