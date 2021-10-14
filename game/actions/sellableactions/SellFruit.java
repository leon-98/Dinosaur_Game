package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.items.Fruit;

/**
 * This class is responsible of sell action for selling fruit
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see SellAction
 */
public class SellFruit extends SellAction {

    /**
     * Constructor
     * @param cost
     */
    public SellFruit(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing fruit was bought
     */
    @Override
    protected String boughtMessage() {
        return "Fruit bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new Fruit();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy fruit
     */
    @Override
    protected String sellMessage() {
        return "Buy Fruit!";
    }
}
