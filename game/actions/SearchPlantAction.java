package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.EcoPoints;
import game.ground.Plant;

/**
 * This class is responsible for search plant action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class SearchPlantAction extends Action {
    /**
     * The object that is treated as a plant
     */
    private Plant plant;
    /**
     * type is str, a description to tell the user
     */
    private final String FAILED= "You search the tree or bush for fruit, but you can’t find any ripe ones.";
    /**
     * type is str, a description to tell the user
     */
    private final String PASSED="You managed to find a ripe fruit!!!";

    /**
     * Constructor
     * @param plant the object treated as a plant
     */
    public SearchPlantAction(  Plant plant) {

        this.plant = plant;
    }

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return passed, a description to tell the user there is no ripe fruits
     * @return failed, a description to tell the user there is ripe fruits
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        double random=0.50;

        if(Math.random() > random){
            actor.addItemToInventory(plant.loseFruit());
            EcoPoints.addPoints(10);
            return PASSED;


        }
        return FAILED;
    }

    /**
     * Responsible to display a menu for the action
     * @param actor the Actor acting
     * @return the actor search the plant
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player search plant";
    }
}
