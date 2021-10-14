package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.items.CarnivoreMealKit;

/**
 * This class is responsible of sell action for selling carnivore meal kit
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see SellAction
 */
public class SellCarnivoreMealKit extends SellAction {

    /**
     * Constructor
     * @param cost
     */
    public SellCarnivoreMealKit(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing carnivore meal kit was bought
     */
    @Override
    protected String boughtMessage() {
        return "Predator Meal Kit bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new CarnivoreMealKit();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy carnivore meal kit
     */
    @Override
    protected String sellMessage() {
        return "Buy Predator Meal Kit";
    }
}

