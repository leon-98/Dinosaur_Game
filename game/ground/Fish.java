package game.ground;

import game.FoodSource;

/**
 * This class is responsible for Fish
 * @author Yi Sen
 * @version 1.0.0
 * @since 18/5/2021
 * @see FoodSource
 */
public class Fish implements FoodSource {
    /**
     * type as int, the nutrition amount
     */
    private int nutrition=100;

    /**
     * Responsible to gain nutrition after eating
     * @return nutrition, the food level to be gained after eating it
     */
    @Override
    public int getNutrition() {
        return nutrition;
    }

    /**
     * reduce nutrition by amount
     * @param amount, an int type that represents the amount to be reduce
     */
    @Override
    public void reduceNutrition(int amount) {
        nutrition=Math.max(0,nutrition-amount);
    }
}
