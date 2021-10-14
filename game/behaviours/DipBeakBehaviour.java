package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkAction;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.ground.LakeStatus;
/**
 * This class is responsible for dipping beak behaviour
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Behaviour
 */
public class DipBeakBehaviour extends Action implements Behaviour {
    private Dinosaur self;

    public DipBeakBehaviour(Dinosaur self) {
        this.self = self;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if cant fly means it shouldnt dip beak and should return null
        if(!self.hasCapability(DinosaurStatus.ISFLYING)||!self.hasCapability(DinosaurStatus.THIRSTY)||!self.hasCapability(DinosaurStatus.HUNGRY))
            return null;
         Location here= map.locationOf(actor);
        // if the dinosaur is flying on the lake, it can drink the water and get allowable actions on that lake its on
        if (here.getGround().hasCapability(LakeStatus.ISLAKE)){
            return this;

        }
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String string="";
        Location here= map.locationOf(actor);
         string+=here.getGround().allowableActions(actor,here,"").get(0).execute(actor,map);
        if(here.getGround().hasCapability(LakeStatus.CANDRINK)) {
            string += "\n" + new DrinkAction(self).execute(actor, map);
        }

        return string;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
