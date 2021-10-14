package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for eat food behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 */
public class EatFoodBehaviour implements Behaviour {
    /**
     * The Dinosaur is self
     */
    private Dinosaur self;

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public EatFoodBehaviour(Dinosaur self) {
        this.self = self;
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return eat food action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return eatFood(map);
    }

    /**
     * Responsible to get the action
     * @param map the map that contains this location
     * @return whether if the actions is allowed
     * @return whether if the actions is allowed at that ground
     * @return null if no actions allowed
     */
    private Action eatFood(GameMap map) {
        Location here = map.locationOf(self);
        if (self.hasCapability(DinosaurStatus.HUNGRY) && self.diet(here)) {
            for (Item item : here.getItems()
            ) {
                if (!item.getAllowableActions().isEmpty()) {
                    return item.getAllowableActions().get(0);
                }
            }
            if (here.getGround().allowableActions(self, here, "").size() != 0) {
                return here.getGround().allowableActions(self, here, "").get(0);
            }
        }
            return null;

    }
}
