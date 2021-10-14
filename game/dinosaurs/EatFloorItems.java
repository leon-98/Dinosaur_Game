package game.dinosaurs;

import edu.monash.fit2099.engine.Item;
import game.FoodSource;


import java.util.List;

/**
 * This class is responsible for checking if the dinosaur can eat the items on floor
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 */
public interface EatFloorItems {
    /**
     * Responsible to check whether the dinosaur can eat the item
     * @param item a physical object in the game world
     */
    boolean canEatObject(Item item);

    /**
     * Responsible to check whether the dinosaur is able to eat the items
     * @param items an array list of a physical object in the game world
     */
     boolean checkEatObjects(List<Item> items);


}
