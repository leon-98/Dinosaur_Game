package game.behaviours;

import edu.monash.fit2099.engine.*;

import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for seek food behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 */
public class SeekFoodBehaviour extends SeekBehaviour {

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public SeekFoodBehaviour(Dinosaur self) {
        super(self);
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return mull
     * @return seek food action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!self.hasCapability(DinosaurStatus.HUNGRY))
            return null;
        return super.getAction(actor, map);
    }

    /**
     * Responsible to check whether there is a goal
     * @param location the Location where the location is to be added
     * @return false, if there is no goal
     * @return true, if there is a goal
     */
    @Override
    protected boolean goal(Location location) {
        if(!self.diet(location))
            return false;
      return  true;
    }
}
