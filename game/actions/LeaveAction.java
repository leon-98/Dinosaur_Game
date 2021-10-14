package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameStatus;

/**
 * This class is responsible for letting user leave game
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class LeaveAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(GameStatus.LEAVEGAME);
        return "Leaves the game";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Leave the game";
    }
}
