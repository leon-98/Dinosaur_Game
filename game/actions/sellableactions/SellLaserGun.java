package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.items.LaserGun;

/**
 * This class is responsible of sell action for selling laser gun
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see SellAction
 */
public class SellLaserGun extends SellAction {

    /**
     * Constructor
     * @param cost
     */
    public SellLaserGun(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing laser gun was bought
     */
    @Override
    protected String boughtMessage() {
        return "Laser Gun bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new LaserGun();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy laser gun
     */
    @Override
    protected String sellMessage() {
        return "Buy Laser Gun";
    }
}
