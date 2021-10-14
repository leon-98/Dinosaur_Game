package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This class is responsible for setting up sandbox mode
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class SandBoxAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        return "SandBox mode selected";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Select SandBox Mode";
    }
}
