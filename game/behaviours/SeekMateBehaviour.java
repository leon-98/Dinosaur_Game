package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for seeking mate behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 */
public class SeekMateBehaviour extends SeekBehaviour  {

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public SeekMateBehaviour(Dinosaur self) {
        super(self);
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return mull
     * @return seek mate action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!self.hasCapability(DinosaurStatus.HORNEY))
            return null;
        return super.getAction(actor, map);
    }

    /**
     * Responsible to check whether there is a goal
     * @param location the Location where the location is to be added
     * @return false, if there is no goal or location does not contain an actor
     * @return true, if there is a goal
     */
    @Override
    public boolean goal(Location location) {
        if(!location.containsAnActor()){
            return false;
        }
        Actor target=self.love(location);
        if (target!=null)
            return true;
        return false;
    }
}
