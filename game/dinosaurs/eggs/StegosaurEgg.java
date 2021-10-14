package game.dinosaurs.eggs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.FoodSource;
import game.FoodType;
import game.actions.EatAction;
import game.actions.FeedDinosaurAction;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.dinosaurs.Stegosaur;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for stegosaur egg
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Egg
 * @see FoodSource
 */
public class StegosaurEgg extends Egg implements FoodSource  {
    /**
     * type as int, the food level amount to restore after eating it
     */
    private  int nutrition=10;
    /**
     * A new array list of type Action is created
     */
    private List<Action> actions= new ArrayList<>();

    /**
     * Constructor
     */
    public StegosaurEgg() {
        super("StegosaurEgg", 's',100);
        addCapability(FoodType.CARNIVORE);
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param currentLocation the Location where the currentLocation is to be added
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(currentLocation.containsAnActor()){
            Actor actor= currentLocation.getActor();
            if(actor.hasCapability(DinosaurStatus.ALLOSAUR)){
                actions.add(new EatAction(this,this,actor));
            }
        }
    }

    /**
     * Responsible to get the baby dinosaur
     * @return the baby stegosaur
     */
    @Override
    protected Dinosaur getDinosaur() {
        dinosaur=new Stegosaur(getRandomGender());
        dinosaur.addCapability(DinosaurStatus.BABY);
        return dinosaur;
    }

    /**
     * Responsible to gain nutrition
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
     * Responsible for a collection of the Actions
     * @return A collection of actions.
     */
    @Override
    public List<Action> getAllowableActions() {
        return actions;
    }

}
