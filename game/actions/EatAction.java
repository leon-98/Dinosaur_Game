package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.FoodSource;

/**
 * This class is responsible for eat action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class EatAction extends Action {
    /**
     * The object that is treated as a food source
     */
private FoodSource foodSource;
    /**
     * The Actor that is a dinosaur
     */
private Actor dinosaur;
    /**
     * The item is empty
     */
private Item item=null;

    /**
     * Constructor
     * @param foodSource the object treated as a food source
     * @param dinosaur the dinosaur acting
     * @param item a physical object in the game world
     */
    public EatAction(FoodSource foodSource, Item item,Actor dinosaur) {
        this.foodSource = foodSource;
        this.dinosaur = dinosaur;
        this.item=item;
    }

    /**
     * Constructor
     * @param foodSource the object treated as a food source
     * @param dinosaur the dinosaur acting
     */
    public EatAction(FoodSource foodSource, Actor dinosaur) {
        this.foodSource = foodSource;
        this.dinosaur = dinosaur;
    }

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return result of the health gained by the dinosaur
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int adder=foodSource.getNutrition();
        if (actor.toString().equalsIgnoreCase("pterodactyl")&& item!=null){
            foodSource.reduceNutrition(10);
            actor.heal(10);
        }
        else{
            foodSource.reduceNutrition(foodSource.getNutrition());
            actor.heal(adder);
        }
        if(foodSource.getNutrition()==0 &&item!=null)
        map.locationOf(actor).removeItem(item);

        return String.format("%s eats and its HP is now %s",dinosaur,dinosaur.getHp());
    }

    /**
     * Responsible to display a menu for the action
     * @param actor the Actor acting
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

}
