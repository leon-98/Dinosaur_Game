package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.dinosaurs.eggs.AllosaurEgg;

/**
 * This class is responsible of sell action for selling allosaur egg
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see SellAction
 */
public class SellAllosaurEgg extends SellAction {

    /**
     * Constructor
     * @param cost
     */
    public SellAllosaurEgg(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing allosaur egg was bought
     */
    @Override
    protected String boughtMessage() {
        return "Allosaur Egg bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new AllosaurEgg();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy allosaur egg
     */
    @Override
    protected String sellMessage() {
        return "Buy Allosaur Egg";
    }

}
