package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for lay egg action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class LayEggAction extends Action {
    /**
     * The Dinosaur is self
     */
    private Dinosaur self;

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public LayEggAction(Dinosaur self) {
        this.self = self;
    }

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return result of laying the egg at a certain location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(DinosaurStatus.GIVEBIRTH);
        actor.removeCapability(DinosaurStatus.PREGNANT);
        map.locationOf(self).addItem(self.getEgg());
        int x = map.locationOf(actor).x();
        int y = map.locationOf(actor).y();

        return String.format("A  %s egg was laid at around (%s,%s)",actor.toString(),x,y);
    }

    /**
     * Responsible to display a menu for the action
     * @param actor the Actor acting
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
