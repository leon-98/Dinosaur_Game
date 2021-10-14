package game.items.corpses;

import edu.monash.fit2099.engine.*;
import game.FoodSource;
import game.FoodType;
import game.PortableItem;
import game.actions.EatAction;
import game.actions.FeedDinosaurAction;
import game.dinosaurs.DinosaurStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for corpse
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Corpse
 * @see PortableItem
 * @see FoodSource
 */
public  abstract class Corpse  extends PortableItem implements FoodSource {
    /**
     * type int, counter value
     */
    private int counter=0;
    /**
     * A new array list of actions of type Action
     */
    private List<Action> actions= new ArrayList<>();
    /**
     * An int, that represents the decay value
     */
    private int decay;
    /**
     * An int, that represents the nutrition value for food level
     */
    private int nutrition;
    /**
     * Constructor
     */
    public Corpse(String name, char displayChar,int decay,int nutrition) {
        super(name, displayChar);
        addCapability(FoodType.CARNIVORE);
        addCapability(FoodType.CORPSE);
        this.decay=decay;
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
        counter+=1;
        actions.clear();
        if(counter==getDecay())
            currentLocation.removeItem(this);
        if(currentLocation.containsAnActor()){
            Actor actor= currentLocation.getActor();
            if(actor.hasCapability(DinosaurStatus.CARNIVORE)){
                actions.add(new EatAction(this,this,actor));
            }
        }
    }
    /**
     * Responsible to get decay
     */
    private  int getDecay(){
        return decay;
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
                if(target.hasCapability(DinosaurStatus.CARNIVORE)){
                    actions.add(new FeedDinosaurAction(this,this,target));
                }
            }
        }
    }
    /**
     * Responsible to gain nutrition after eating
     * @return  nutrition gained after eating
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
