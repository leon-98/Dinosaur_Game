package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * This class is responsible for swallowing action
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class SwallowAction extends Action {
    /**
     * the target thats about to be swallowed
     */
    private Actor target;

    /**
     * constructor for SwallowAction
     * @param target An actor, the target thats about to be swallowed
     */
    public SwallowAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(target.getHp());
        map.removeActor(target);
        return String.format("%s swallows %s and %s food level is now %s",actor,target,actor,actor.getHp());
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
