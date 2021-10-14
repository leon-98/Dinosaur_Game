package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.EcoPoints;
import game.FoodSource;

/**
 * This class is responsible for feed dinosaur action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class FeedDinosaurAction extends Action {
    /**
     * The object that is treated as a food source
     */
    private FoodSource foodSource;
    /**
     * The item is a physical object in the game world
     */
    private Item item;
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Constructor
     * @param foodSource the object treated as a food source
     * @param target the actor that is to be attacked
     * @param item a physical object in the game world
     */
    public FeedDinosaurAction(FoodSource foodSource, Item item, Actor target) {
        this.foodSource = foodSource;
        this.item = item;
        this.target = target;
    }

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return result of what happened to health of the dinosaur
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int gainLife = foodSource.getNutrition();
        int originalHp=target.getHp();
        if(item.toString().equalsIgnoreCase("fruit")) {
            gainLife = 20;

        }
        EcoPoints.addPoints(10);
        target.heal(gainLife);
        actor.removeItemFromInventory(item);
        return String.format("%s feeds %s, hp from %s became %s",actor,target,originalHp,target.getHp());
    }

    /**
     * Responsible to display a menu for the action
     * @param actor the Actor acting
     * @return the actor feeds the target
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " feed "+ target.toString();
    }
}
