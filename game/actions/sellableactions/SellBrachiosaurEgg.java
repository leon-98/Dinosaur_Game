package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.dinosaurs.eggs.BrachiosaurEgg;

/**
 * This class is responsible of sell action for selling brachiosaur egg
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see SellAction
 */
public class SellBrachiosaurEgg extends SellAction {

    /**
     * Constructor
     * @param cost
     */
    public SellBrachiosaurEgg(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing brachiosaur egg was bought
     */
    @Override
    protected String boughtMessage() {
        return "Brachiosaur Egg bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new BrachiosaurEgg();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy brachiosaur egg
     */
    @Override
    protected String sellMessage() {
        return "Buy Brachiosaur Egg";
    }
}
