package game.dinosaurs;

import edu.monash.fit2099.engine.Actor;
import game.FoodSource;

/**
 * This class is responsible for conditions of predator dinosaurs
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 */
public interface Predator {
    /**
     * Responsible to check whether the dinosaur can eat the prey
     * @param actor the actor acting
     */
    boolean canEatPrey(Actor actor);
    /**
     * Responsible to remember the prey based on their unique ID
     * @param dinosaurId the unique ID for each dinosaur
     */
 void rememberPrey(int dinosaurId);
}
