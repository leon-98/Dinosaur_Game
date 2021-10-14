package game.dinosaurs;


import edu.monash.fit2099.engine.*;
import game.*;

import game.actions.BiteAction;
import game.dinosaurs.eggs.Egg;
import game.dinosaurs.eggs.StegosaurEgg;
import game.ground.GroundStatus;


import java.util.List;

/**
 * This class is responsible for stegosaur dinosaur
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Dinosaur
 * @see Herbivore
 * @see EatFloorItems
 */
public class Stegosaur extends Dinosaur implements Herbivore, EatFloorItems {
    /**
     * type as int, the starting health of the stegosaur
     */
    private final int STARTING_HP = 50;
    /**
     * type as int, the hunger level of the stegosaur
     */
    private final int HUNGER_LEVEL = 90;
    /**
     * type as int, the horney level of the stegosaur
     */
    private final int HORNEY_LEVEL = 50;
    /**
     * type as int, the evolve level of the stegosaur
     */
    private final int EVOLVE_LEVEL = 30;
    /**
     * type as int, the unconscious level of the stegosaur
     */
    private final int UNCONSCIOUS_LEVEL = 20;
    /**
     * type as int, the lay egg level of the stegosaur
     */
    private final int LAY_EGG_LEVEL = 10;
    /**
     * A new stegosaurEgg of type Egg is created
     */
    private Egg stegosaurEgg = new StegosaurEgg();
    /**
     * Constructor of Stegosaur
     * @param gender the gender of the Stegosaur
     */
    public Stegosaur(Gender gender) {
        super("Stegosaur", 'S', 100, gender,100);
        addCapability(DinosaurStatus.PREY);
        addCapability(DinosaurStatus.HERBIVORE);
        addCapability(DinosaurStatus.STEGOSAUR);

    }

    /**
     * Responsible to check whether can the dinosaur eat the object
     * @param location the Location where the location is to be added
     * @return true, if the object is edible
     * @return true, if the plant is edible
     * @return false, the object and plant is not edible
     */
    @Override
    public boolean diet(Location location) {
        if (checkEatObjects(location.getItems()))

            return true;
        if (canEatPlant(location.getGround()))
            return true;

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
     * @return the stegosaur egg
     */
    @Override
    public Egg getEgg() {
        egg = stegosaurEgg;
        return egg;
    }

    /**
     * Responsible to check whether the dinosaur is able to eat the object
     * @param items an array list of a physical object in the game world
     * @return true, if the dinosaur is able to eat the object
     * @return false, if the dinosaur is not able to eat the object
     */
    public boolean checkEatObjects(List<Item> items) {
        for (Item item : items) {
            if (canEatObject(item))
                return true;

        }
        return false;
    }

    /**
     * Responsible to check whether the dinosaur can eat the item for its type
     * @param item a physical object in the game world
     * @return true, if the dinosaur can eat the item
     * @return false, if the dinosaur can not eat the item
     */
    public boolean canEatObject(Item item) {
        if (item.hasCapability(FoodType.HERBIVORE))
            return true;
        return false;
    }

    /**
     * Responsible to check whether the dinosaur can eat the plant
     * @param ground the ground of the map
     * @return true, if there are fruits on bush
     * @return false, if there are no fruits on bush
     */
    @Override
    public boolean canEatPlant(Ground ground) {
        if (ground.hasCapability(GroundStatus.BUSHHASFRUIT))
            return true;
        return false;
    }

    /**
     * Responsible to check whether the dinosaur hungry
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
     * Responsible to get the health of the allosaur dinosaur
     * @return the health of the dinosaur
     */
    @Override
    public int getHp() {
        return hitPoints;
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
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new BiteAction(this));

    }
}
