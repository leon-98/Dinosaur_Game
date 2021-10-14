package game.dinosaurs.eggs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.PortableItem;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Gender;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is responsible for egg
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see PortableItem
 */
public abstract class Egg extends PortableItem {
    /**
     * Random number generator
     */
    private Random random = new Random();
    /**
     * type as int, hatch timer to hatch the gg
     */
    protected int hatchTimer=0;
    /**
     * A new array list of type Gender is created
     */
    protected ArrayList<Gender> genders= new ArrayList<Gender>();
    /**
     * The dinosaur when the egg hatches
     */
    protected Dinosaur dinosaur;
/**
 * An int, that represents the ecoPoints gained when egg hatches
 */
private int incrementPoints;
    /**
     * Constructor
     * @param displayChar the display character of the object
     * @param name the name of the object
     */
    public Egg(String name, char displayChar,int incrementPoints) {
        super(name, displayChar);
        this.incrementPoints=incrementPoints;
        setUpGenders();
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        incrementHatchTimer();
        hatch(currentLocation);
    }

    /**
     * Responsible to hatch the dinosaur egg
     * @param location the Location where the location is to be added
     */
    private void hatch(Location location) {
        if(canHatch()&& !location.containsAnActor()) {
            location.addActor(getDinosaur());
            location.removeItem(this);
            incrementPoints();

        }
    }

    /**
     * Responsible to increase hatch timer
     */
    protected void incrementHatchTimer(){
        hatchTimer+=1;
    }

    /**
     * Responsible to check whether is egg ready to hatch
     * @return true, if the egg is ready to hatch
     * @return false, if the egg is not ready to hatch
     */
    protected boolean canHatch(){
        if(hatchTimer>=5)
            return true;
        return false;
    }

    /**
     * Responsible to set the gender of the egg
     */
    private void setUpGenders(){
        genders.add(Gender.MALE);
        genders.add(Gender.FEMALE);
    }

    /**
     * Responsible to get a random gender
     * @return the gender of the egg
     */
    protected Gender getRandomGender(){
       return genders.get(random.nextInt(genders.size()));
    }

    /**
     * Responsible to get a dinosaur
     */
    protected abstract Dinosaur getDinosaur();

    /**
     * Responsible to increase EcoPoints
     */
    protected void incrementPoints(){
        EcoPoints.addPoints(incrementPoints);
    }
}
