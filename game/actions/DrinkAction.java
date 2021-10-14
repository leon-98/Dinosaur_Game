package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
/**
 * This class is responsible for drinking action
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class DrinkAction extends Action {
    /**
     * the dinosaur self
     */
    private Dinosaur self;

    /**
     * constructor for DrinkcAction
     * @param self, the Dinosaur's self
     */
    public DrinkAction(Dinosaur self) {
        this.self = self;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(self.toString().equalsIgnoreCase("Brachiosaur")){
            self.addWaterLevel(80);
        }
        else{
            self.addWaterLevel(30);
        }
        return String.format("%s drinks water and its water level is now %s",actor.toString(),self.getWaterLevel());
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

}
