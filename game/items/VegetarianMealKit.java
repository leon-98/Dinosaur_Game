package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.FoodSource;
import game.FoodType;
import game.PortableItem;
import game.actions.FeedDinosaurAction;
import game.dinosaurs.DinosaurStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for vegetarian meal kit
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see PortableItem
 * @see FoodSource
 */
public class VegetarianMealKit extends PortableItem implements FoodSource {
    /**
     * A new array list of actions of type Action
     */
    private List<Action> actions= new ArrayList<>();
    /**
     * an int value represents the food level amount to be restored
     */
    private int nutrition;
    /**
     * Constructor
     */
    public VegetarianMealKit() {
        super("VegetarianMealKit", 'v');
        addCapability(FoodType.HERBIVORE);
    }

    /**
     * Responsible to gain food level after eating
     * @return nutrition, the food level to be gained after eating it
     */
    @Override
    public int getNutrition() {
        return nutrition;
    }

    /**
     * reduce nutrition by amount
     * @param amount, an int type that represents the amount to be reduce
     */
    @Override
    public void reduceNutrition(int amount) {
        nutrition=Math.max(0,nutrition-amount);
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param currentLocation the Location where the currentLocation is to be added
     * @param actor the Actor acting
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        actions.clear();
        for (Exit direction:currentLocation.getExits()) {
            Location there=direction.getDestination();
            if(there.containsAnActor()){
                Actor target=there.getActor();
                if(target.hasCapability(DinosaurStatus.HERBIVORE)){
                    nutrition=target.getHp();
                    actions.add(new FeedDinosaurAction(this,this,target));
                }
            }
        }
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor
     * @return a collection of actions
     */
    @Override
    public List<Action> getAllowableActions() {
        return actions;
    }
}
