package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.MateAction;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for mating behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 */
public class MatingBehaviour implements Behaviour {
    /**
     * The Dinosaur is self
     */
    private Dinosaur self;

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public MatingBehaviour(Dinosaur self) {
        this.self = self;
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return mate  action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here =map.locationOf(actor);
        return getMate(here);
    }

    /**
     * Responsible to check lover
     * @param here the Location where here is to be added
     * @return mate action if can mate
     * @return null if cant mate
     */
    private Action getMate(Location here){
        for (Exit exit:here.getExits()) {
            Location destination= exit.getDestination();
            Actor target=getLover(destination);

            if(target!=null && isNotPregnant(target)&& self.hasCapability(DinosaurStatus.HORNEY)) {
                return new MateAction(getLover(destination), self);
            }
        }
        return null;
    }

    /**
     * Responsible to get lover
     * @param destination the Location where destination is to be added
     * @return get distance of the partner
     */
    private Actor getLover(Location destination){

        return self.love(destination);
    }

    /**
     * Responsible to check whether is the dinosaur pregnant
     * @param target the Actor that is a target
     * @return false, the target dinosaur is not pregnant
     * @return false, the dinosaur self is not pregnant
     * @return true, the dinosaur is pregnant
     */
    private boolean isNotPregnant(Actor target){
        if(target.hasCapability(DinosaurStatus.PREGNANT))
            return false;
        if(self.hasCapability(DinosaurStatus.PREGNANT))
            return  false;
        return  true;
    }
}
