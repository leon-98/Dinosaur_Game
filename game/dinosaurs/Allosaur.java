package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.FoodType;

import game.behaviours.PredatorBehaviour;
import game.dinosaurs.eggs.AllosaurEgg;
import game.dinosaurs.eggs.Egg;

import java.util.List;

/**
 * This class is responsible for allosaur dinosaur
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Dinosaur
 * @see Predator
 * @see EatFloorItems
 */
public class Allosaur extends Dinosaur implements Predator, EatFloorItems {
    /**
     * type as int, the starting health of the allosaur
     */
    private final int STARTING_HP = 50;
    /**
     * type as int, the hunger level of the allosaur
     */
    private final int HUNGER_LEVEL = 90;
    /**
     * type as int, the horney level of the allosaur
     */
    private final int HORNEY_LEVEL = 50;
    /**
     * type as int, the evolve level of the allosaur
     */
    private final int EVOLVE_LEVEL = 50;
    /**
     * type as int, the unconscious level of the allosaur
     */
    private final int UNCONSCIOUS_LEVEL = 20;
    /**
     * type as int, the lay egg level of the allosaur
     */
    private final int LAY_EGG_LEVEL = 20;
    /**
     * The object is preyManager
     */
    private PreyManager preyManager;
    /**
     * A new allosaurEgg of type Egg is created
     */
    private Egg allosaurEgg = new AllosaurEgg();

    /**
     * Constructor of Allosaur
     * @param gender
     */
    public Allosaur(Gender gender) {
        super("Allosaur", 'A', 100, gender,100);
        preyManager = new PreyManager();
        behaviours.add(4, new PredatorBehaviour(this));
        addCapability(DinosaurStatus.CARNIVORE);
        addCapability(DinosaurStatus.ALLOSAUR);
    }

    /**
     * Responsible to select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        preyManager.decrementTurn();
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Responsible to represents a weapon for an unarmed Actor
     * @return the damage inflicted and the verb as display
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "Bites");
    }

    /**
     * Responsible to check whether can the dinosaur eat the object
     * @param location the Location where the location is to be added
     * @return true, if the object is edible
     * @return true, if the prey is edible
     * @return false, the item and prey is not edible
     */
    @Override
    public boolean diet(Location location) {
        if( checkEatObjects(location.getItems()))
            return  true;

        if(location.containsAnActor()) {
            if (canEatPrey(location.getActor()))
                return true;
        }

        return false;

    }

    /**
     * Responsible to check whether can the dinosaur give birth
     * @return true, if the dinosaur can give birth
     * @return false, if the dinosaur cannot give birth
     */
    @Override
    protected boolean canBirth() {
        if (layEggTimer == LAY_EGG_LEVEL)
            return true;
        return false;
    }

    /**
     * Responsible to get the egg
     * @return the allosaur egg
     */
    @Override
    public Egg getEgg() {
        egg = allosaurEgg;
        return egg;
    }

    /**
     * Responsible to check whether is the dinosaur hungry
     * @return true, if the dinosaur is hungry
     * @return false, if the dinosaur is not hungry
     */
    @Override
    protected boolean isHungry() {
        if (hitPoints < HUNGER_LEVEL)
            return true;
        return false;
    }

    /**
     * Responsible to check whether is the dinosaur starving to death
     * @return true, if the dinosaur is unconscious
     * @return false, if the dinosaur is conscious
     */
    @Override
    protected boolean isStarveDeath() {
        if (deathTimer == UNCONSCIOUS_LEVEL)
            return true;
        return false;
    }

    /**
     * Responsible to check whether the dinosaur wants to mate
     * @return true, if the dinosaur wants to mate
     * @return false, if the dinosaur does not wants to mate
     */
    @Override
    protected boolean isHorney() {
        if (hitPoints > HORNEY_LEVEL)
            return true;
        return false;
    }

    /**
     * Responsible to check whether can the dinosaur evolve
     */
    @Override
    protected void canEvolve() {
        if (evolveTimer == EVOLVE_LEVEL)
            setAdult();
    }

    /**
     * Responsible to evolve the dinosaur
     */
    @Override
    protected void setAdult() {
        super.setAdult();
        this.hitPoints = STARTING_HP;
    }

    /**
     * Responsible to check whether the dinosaur can eat the prey
     * @param actor the actor acting
     * @return true, if the dinosaur can eat the prey
     * @return false, if the dinosaur cannot eat the prey
     */
    @Override
    public boolean canEatPrey(Actor actor) {
        if (actor.hasCapability(DinosaurStatus.PREY) && !preyManager.gotPrey(actor.getId()))
            return true;
        return false;
    }

    /**
     * Responsible to remember the prey based on their unique ID
     * @param dinosaurId the unique ID for each dinosaur
     */
    @Override
    public void rememberPrey(int dinosaurId) {
        preyManager.addPrey(dinosaurId);
    }

    /**
     * Responsible to check whether the dinosaur can eat the item for its type
     * @param item a physical object in the game world
     * @return true, if the dinosaur can eat the object
     * @return false, if the dinosaur can not eat the object
     */
    @Override
    public boolean canEatObject(Item item) {
        if (item.hasCapability(FoodType.CARNIVORE))
            return true;
        return false;
    }

    /**
     * Responsible to check whether the dinosaur is able to eat the items
     * @param items an array list of a physical items in the game world
     * @return true, if the dinosaur is able to eat the object
     * @return false, if the dinosaur is not able to eat the object
     */
    @Override
    public boolean checkEatObjects(List<Item> items) {
        for (Item item : items) {
            if (canEatObject(item))
                return  true;
        }
        return false;
    }

    /**
     * Responsible to get the health of the allosaur dinosaur
     * @return the health of the dinosaur
     */
    @Override
    public int getHp() {
        return hitPoints;
    }

}
