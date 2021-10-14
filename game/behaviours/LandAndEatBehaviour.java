package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.dinosaurs.EatFloorItems;
/**
 * This class represents a behaviour of  Dinosaur landing and then eat
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Behaviour
 */
public class LandAndEatBehaviour implements Behaviour {
    private EatFloorItems eatFloorItems;
    private Dinosaur self;

    public LandAndEatBehaviour(EatFloorItems eatFloorItems, Dinosaur self) {
        this.eatFloorItems = eatFloorItems;
        this.self = self;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here=map.locationOf(actor);
        // check if here has edible items
        if(eatFloorItems.checkEatObjects(here.getItems())){
            // the moment surrounding has an actor, return null
            for (Exit direction:here.getExits()
                 ) {
                    if (direction.getDestination().containsAnActor()){
                        return null;
                }
            }
            actor.removeCapability(DinosaurStatus.ISFLYING);
            return new EatFoodBehaviour(self).getAction(actor,map);

        }
        return null;
    }
}
