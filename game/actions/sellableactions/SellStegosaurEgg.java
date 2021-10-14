package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.dinosaurs.eggs.StegosaurEgg;

/**
 * This class is responsible of sell action for selling stegosaur egg
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see SellAction
 */
public class SellStegosaurEgg extends SellAction {

    /**
     * Constructor
     * @param cost
     */
    public SellStegosaurEgg(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing stegosaur egg was bought
     */
    @Override
    protected String boughtMessage() {
        return "Stegosaur Egg bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new StegosaurEgg();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy stegosaur egg
     */
    @Override
    protected String sellMessage() {
        return "Buy Stegosaur Egg";
    }
}
