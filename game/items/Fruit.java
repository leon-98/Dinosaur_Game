package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.FoodSource;
import game.FoodType;
import game.PortableItem;
import game.actions.EatAction;
import game.actions.FeedDinosaurAction;
import game.dinosaurs.DinosaurStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for fruit
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see PortableItem
 * @see FoodSource
 */
public class Fruit extends PortableItem implements FoodSource {

    /**
     * type int, decay of the corpse
     */
 private final int DECAY= 15;

    /**
     * type int, value of count
     */
 private int count=0;
    /**
     * An int value that represents the food level amount to be restored
     */
 private int nutrition;
    /**
     * A new array list of actions of type Action
     */
 private List<Action> actions= new ArrayList<>();

    /**
     * Constructor
     */
    public Fruit() {
        super("fruit", 'F' );
        hasCapability(FoodType.HERBIVORE);
        nutrition=10;
    }
    /**
     * Constructor
     * @param nutrition int value that represents the amount of food level to restore
     */
    public Fruit(int nutrition) {
        super("fruit", 'F');
        hasCapability(FoodType.HERBIVORE);
        this.nutrition=nutrition;
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param currentLocation the Location where the currentLocation is to be added
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        count+=1;
        actions.clear();
        if(count==DECAY)
            currentLocation.removeItem(this);
        if(currentLocation.containsAnActor()){
            Actor actor= currentLocation.getActor();
            if(actor.hasCapability(DinosaurStatus.STEGOSAUR)){
                actions.add(new EatAction(this,this,actor));
            }
        }
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
                    actions.add(new FeedDinosaurAction(this,this,target));
                }
            }
        }
    }

    /**
     * Responsible to gain nutrition after eating
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
     * Returns a collection of the Actions that the otherActor can do to the current Actor
     * @return a collection of actions
     */
    @Override
    public List<Action> getAllowableActions() {
        return actions;
    }
}
