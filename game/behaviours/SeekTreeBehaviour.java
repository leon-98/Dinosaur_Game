package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.ground.GroundStatus;
/**
 * This class is responsible for seeking tree behaviour
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see SeekBehaviour
 */
public class SeekTreeBehaviour extends SeekBehaviour {
    /**
     * Constructor for SeekTreeBehaviour
     *
     * @param self the dinosaur itself
     */
    public SeekTreeBehaviour(Dinosaur self) {
        super(self);
    }

    @Override
    protected boolean goal(Location location) {
        // if location doesnt have an actor on it and the ground is a tree, then return true
        if(!location.containsAnActor()){
            if(location.getGround().hasCapability(GroundStatus.ISTREE)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if dinosaur can fly, return null since it should only seek tree if cant fly
        if(self.hasCapability(DinosaurStatus.ISONTREE)|| self.hasCapability(DinosaurStatus.ISFLYING)&&!self.hasCapability(DinosaurStatus.HORNEY) )
            return null;
        return super.getAction(actor, map);
    }
}
