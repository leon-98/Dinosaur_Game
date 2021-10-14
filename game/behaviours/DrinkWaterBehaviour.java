package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkAction;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.ground.LakeStatus;
/**
 * This class is responsible for drinking water behaviour
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Behaviour
 */
public class DrinkWaterBehaviour implements Behaviour{
    private Dinosaur self;

    public DrinkWaterBehaviour(Dinosaur self) {
        this.self = self;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here= map.locationOf(actor);
        // search surrounding for lake and if got, return DrinkAction
        if(self.hasCapability(DinosaurStatus.THIRSTY)) {
            for (Exit direction : here.getExits()) {
                Location destination = direction.getDestination();
                if (destination.getGround().hasCapability(LakeStatus.ISLAKE) && destination.getGround().hasCapability(LakeStatus.CANDRINK)) {
                    destination.getGround().addCapability(LakeStatus.SIPS);
                    return new DrinkAction(self);
                }

            }
        }
        return null;
    }
}
