package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.DipBeakAction;
import game.dinosaurs.DinosaurStatus;


import java.util.ArrayList;

/**
 * This class is responsible for Lake
 * @author Yi Sen
 * @version 1.0.0
 * @since 18/5/2021
 * @see Ground
 */
public class Lake extends Ground {

    /**
     * type as int, the max capacity for fish
     */
    private final int MAX_FISH_CAPACITY = 25;
    /**
     * type as int, the max capacity of sips
     */
    private final int SIPS_CAPACITY = 25;
    /**
     * type as int, the starting amount of sips
     */
    private int sips = 25;
    /**
     * A new array list of fishes of type Fish
     */
    private ArrayList<Fish> fishes = new ArrayList<>();

    /**
     * Constructor
     */
    public Lake() {
        super('~');
        addCapability(LakeStatus.ISLAKE);
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param location the Location where the location is to be added
     */
    public void tick(Location location) {
        super.tick(location);
        drank();
        createFish();
        hasFishes();
        hasSips();
        refillLake();
        addStarterFishes();
        spawnFish();
    }

    /**
     * reduce sips if dinosaur sips on it
     */
    private void drank(){
        if(hasCapability(LakeStatus.SIPS)){
            reduceSips();
            removeCapability(LakeStatus.SIPS);
        }
    }
    /**
     * Responsible to refill the lake
     */
    private void refillLake(){
        if (hasCapability(LakeStatus.ADDWATER)) {
            double min = 0.1;
            double max = 0.6;
            double randomValue = min + (max - min) * Math.random();
            double output = randomValue * 20;
            int rainAmount = (int) Math.round(output);
            sips=Math.min(SIPS_CAPACITY,sips+rainAmount);
            removeCapability(LakeStatus.ADDWATER);
        }
    }

    /**
     * Responsible to reduce the amount of sips
     */
    public void reduceSips(){
        sips = Math.max(0,sips-1);
    }

    /**
     * Responsible to create fish
     */
    private void createFish(){
       if (fishes.size() < MAX_FISH_CAPACITY) {
           fishes.add(new Fish());
       }
    }

    /**
     * Responsible to add 5 fishes into the lake at the start
     */
    private void addStarterFishes() {
        for (int i = 0; i < 5; i++) {
            createFish();
        }
    }

    /**
     * Responsible to create new fishes
     */
    private void spawnFish(){
        double stats = 40;
        if (Math.random() > stats){
            createFish();
        }
    }

    /**
     * Responsible to check if the lake has fish
     */
    private void hasFishes(){
        if (fishes.size() > 0){
            addCapability(LakeStatus.LAKEHASFISH);
        }
        else{
            removeCapability(LakeStatus.LAKEHASFISH);
        }
    }

    /**
     * if has sips add CANDRINK capability else remove it
     */
    private void hasSips(){
        if (sips> 0){
            addCapability(LakeStatus.CANDRINK);
        }
        else{
            removeCapability(LakeStatus.CANDRINK);
        }
    }

    /**
     * Responsible to check if the actor can enter
     * @param actor the actor is the dinosaur
     * @return true if the dinosaur can enter the lake
     * @return false if the dinosaur cannot enter the lake
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(DinosaurStatus.ISFLYING)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * give fishes to the dinosaur
     * @return An arraylist of Fish, where size is 0 to 2
     */
    private ArrayList<Fish> giveFishes(){
        int amount =(int)Math.round(((Math.random()*(2-0))+0));
        ArrayList<Fish>giveFishes= new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if(fishes.size()>0){
                giveFishes.add(fishes.get(0));
                fishes.remove(0);
            }

        }
        return giveFishes;
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param actor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param location   the Location where the location is to be added
     * @return A new action call dip beak action that gives fish to the pterodactyl dinosaur
     * @return A collection of actions.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(actor.toString().equalsIgnoreCase("Pterodactyl"))
            return new Actions(new DipBeakAction(giveFishes()));
        return super.allowableActions(actor, location, direction);
    }
}
