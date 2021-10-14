package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.eggs.BrachiosaurEgg;
import game.dinosaurs.eggs.Egg;
import game.ground.GroundStatus;

/**
 * A long neck and herbivores dinosaur
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Dinosaur
 * @see Herbivore
 */
public class Brachiosaur extends Dinosaur implements Herbivore {
    /**
     * Starting health / food level of adult Brachiosaur is 100
     */
    private final int  STARTING_HP = 100;
    /**
     * an int value that represents when it will be hungry
     */
    private final int HUNGER_LEVEL=140;
    /**
     * an int value that represents when it will be horney
     */
    private final int HORNEY_LEVEL= 70;
    /**
     * an int value that represents when it will evolve
     */
    private final int EVOLVE_LEVEL=50;
    /**
     * an int value that represents when it will be unconscious
     */
    private final int UNCONSCIOUS_LEVEL=20;
    /**
     * an int value that represents when it will lay egg
     */
    private final int LAY_EGG_LEVEL=30;
    /**
     * A new brachiosaurEgg of type Egg is created
     */
    private Egg brachiosaurEgg= new BrachiosaurEgg();

    /**
     * Constructor for Brachiosaur
     * @param gender
     */
    public Brachiosaur( Gender gender) {
        super("Brachiosaur", 'B', 160, gender,200);
        addCapability(DinosaurStatus.HERBIVORE);
        addCapability(DinosaurStatus.BRACHIOSAUR);

    }

    /**
     * Responsible to check whether can the dinosaur eat the item
     * @param location the Location where the location is to be added
     * @return flag a boolean, if the plant is edible returns true else false
     */
    @Override
    public boolean diet(Location location) {
        boolean flag=canEatPlant(location.getGround());

        return flag;
    }

    /**
     * Responsible to check whether can the dinosaur give birth
     * @return true, if the dinosaur can give birth
     * @return false, if the dinosaur cannot give birth
     */
    @Override
    protected boolean canBirth() {
        if(layEggTimer==LAY_EGG_LEVEL)
            return  true;
        return false;
    }

    /**
     * Responsible to get the egg
     * @return the allosaur egg
     */
    @Override
    public Egg getEgg() {
        egg=brachiosaurEgg;
        return egg;
    }

    /**
     * Responsible to get the egg
     * @return the allosaur egg
     */
    @Override
    protected boolean isHungry() {
        if(hitPoints<HUNGER_LEVEL)
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
        if(deathTimer==UNCONSCIOUS_LEVEL)
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
        if(hitPoints>HORNEY_LEVEL)
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
     * Responsible to check whether the dinosaur can eat the plant
     * @param ground the ground of the map
     * @return true, if the dinosaur can eat the plant
     * @return false, if the dinosaur cannot eat the plant
     */
    @Override
    public boolean canEatPlant(Ground ground) {
        if (ground.hasCapability(GroundStatus.TREEHASFRUIT))
            return true;
        return false;
    }

    /**
     * Responsible to get the health of the brachiosaur dinosaur
     * @return the health of the dinosaur
     */
    @Override
    public int getHp() {
        return hitPoints;
    }
}
