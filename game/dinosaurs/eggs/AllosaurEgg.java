package game.dinosaurs.eggs;

import edu.monash.fit2099.engine.Location;
import game.FoodSource;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for allosaur egg
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Egg
 */
public class AllosaurEgg extends Egg  {

    /**
     * Constructor
     */
    public AllosaurEgg() {
        super("AllosaurEgg", 'a',1000);
    }



    /**
     * Responsible to get the baby dinosaur
     * @return the baby allosaur
     */
    @Override
    protected Dinosaur getDinosaur() {
        dinosaur=new Allosaur(getRandomGender());
        dinosaur.addCapability(DinosaurStatus.BABY);
        return dinosaur;
    }




}
