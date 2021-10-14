package game;

/**
 * This class is responsible for gaining nutrition
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 */
public interface FoodSource {
    /**
     * Responsible to gain nutrition after eating
     */
    int getNutrition();

    /**
     * reduces nutrition by amount
     * @param amount, an int type that represents the amount to be reduce
     */
    void reduceNutrition(int amount);


}

