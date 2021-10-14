package game.dinosaurs;

import edu.monash.fit2099.engine.Ground;
import game.FoodSource;

/**
 * This class is responsible for conditions of herbivore dinosaurs
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 */
public interface Herbivore {
     /**
      * Responsible to check whether the dinosaur can eat the plant
      * @param ground the ground of the map
      */
     boolean canEatPlant(Ground ground);
}
