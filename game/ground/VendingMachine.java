package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.actions.sellableactions.*;

import java.util.ArrayList;

/**
 * This class is responsible for Vending Machine
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see Ground
 */
public class VendingMachine extends Ground {
    /**
     * The object is allowableActions
     */
private Actions allowableActions;
    /**
     * type int, eco points for the fruit
     */
private final int FRUIT=30;
    /**
     * type int, eco points for the vegetarian meal kit
     */
private final int VEGETARIAN_MEAL_KIT=100;
    /**
     * type int, eco points for the carnivore meal kit
     */
private final int CARNIVORE_MEAL_KIT=500;
    /**
     * type int, eco points for the stegosaur egg
     */
private  final int STEGOSAUR_EGG=200;
    /**
     * type int, eco points for the brachiosaur egg
     */
private final int BRACHIOSAUR_EGG=500;
    /**
     * type int, eco points for the allosaur egg
     */
private  final  int ALLOSAUR_EGG=1000;
    /**
     * type int, eco points for the laser gun
     */
private final int LASER_GUN=500;
    /**
     * type int, eco points for the pterodactyl egg
     */
    private final int PTERODACTYL_EGG=200;
    /**
     * A new array list of sell action of type SellAction
     */
private ArrayList<SellAction> sellActions= new ArrayList<>();

    /**
     * Constructor
     */
    public VendingMachine() {
        super('H');
        allowableActions= new Actions();
        sellActions.add(new SellFruit(FRUIT));
        sellActions.add(new SellAllosaurEgg(ALLOSAUR_EGG));
        sellActions.add(new SellBrachiosaurEgg(BRACHIOSAUR_EGG));
        sellActions.add(new SellCarnivoreMealKit(CARNIVORE_MEAL_KIT));
        sellActions.add(new SellLaserGun(LASER_GUN));
        sellActions.add(new SellStegosaurEgg(STEGOSAUR_EGG));
        sellActions.add(new SellVegetarianMealKit(VEGETARIAN_MEAL_KIT));
        sellActions.add(new SellPterodactylEgg(PTERODACTYL_EGG));

    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param location the Location where the location is to be added
     */
    public void tick(Location location){
        super.tick(location);
        allowableActions.clear();
        if(location.containsAnActor()){
            Actor actor= location.getActor();
            if(actor.toString().equalsIgnoreCase("player")){
                int currency=EcoPoints.getPoints();
                if(currency>=FRUIT){
                    allowableActions.add(sellActions.get(0));
                }
                if(currency>=ALLOSAUR_EGG){
                    allowableActions.add(sellActions.get(1));
                }
                if(currency>=BRACHIOSAUR_EGG){
                    allowableActions.add(sellActions.get(2));
                }
                if(currency>=CARNIVORE_MEAL_KIT){
                    allowableActions.add(sellActions.get(3));
                }
                if(currency>=LASER_GUN){
                    allowableActions.add(sellActions.get(4));
                }
                if(currency>=STEGOSAUR_EGG){
                    allowableActions.add(sellActions.get(5));
                }
                if(currency>=VEGETARIAN_MEAL_KIT){
                    allowableActions.add(sellActions.get(6));
                }
                if(currency>=PTERODACTYL_EGG){
                    allowableActions.add(sellActions.get(7));
                }
            }
        }
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor
     *
     * @param actor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param location   the Location where the location is to be added
     * @return A collection of actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return allowableActions;
    }
}
