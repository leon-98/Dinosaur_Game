package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.ground.LakeStatus;
/**
 * This class is responsible for seeking lake behaviour
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see SeekBehaviour
 */
public class SeekLakeBehaviour extends SeekBehaviour {
    /**
     * Constructor
     *
     * @param self the dinosaur itself
     */
    public SeekLakeBehaviour(Dinosaur self) {
        super(self);
    }

    @Override
    protected boolean goal(Location location) {
        //if the location is a lake, seek it
            if(location.getGround().hasCapability(LakeStatus.ISLAKE)){
                return true;
            }

        return false;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if dinosaur not thirsty return null
        if(!self.hasCapability(DinosaurStatus.THIRSTY))
            return  null;
        return super.getAction(actor, map);
    }
}
