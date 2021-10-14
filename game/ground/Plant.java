package game.ground;

import game.items.Fruit;

/**
 * This class is responsible for creating fruits and to lose fruits
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 */
public interface Plant {
    /**
     * Responsible to create fruits
     */
    void createFruits();
    /**
     * Responsible to lose fruits
     */
    Fruit loseFruit( );
}
