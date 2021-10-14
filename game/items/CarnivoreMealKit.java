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
import game.items.corpses.Corpse;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for carnivore meal kit
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see PortableItem
 * @see FoodSource
 */
public class CarnivoreMealKit extends PortableItem implements FoodSource {
    /**
     * A new array list of actions of type Action
     */
    private List<Action> actions= new ArrayList<>();
    /**
     * An int value that represents the food level amount to be restored
     */
    private int nutrition;
    /**
     * Constructor
     */
    public CarnivoreMealKit() {
        super("CarnivoreMealKit", 'c');
        addCapability(FoodType.CARNIVORE);
    }

    /**
     * Responsible to gain nutrition after eating
     * @return 350, nutrition gained after eating
     */
    @Override
    public int getNutrition() {
        return nutrition;
    }

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
                nutrition=target.getHp();
                if(target.hasCapability(DinosaurStatus.CARNIVORE)){
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
