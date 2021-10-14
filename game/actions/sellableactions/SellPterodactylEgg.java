package game.actions.sellableactions;

import edu.monash.fit2099.engine.Item;
import game.dinosaurs.eggs.PterodactylEgg;

public class SellPterodactylEgg  extends SellAction{
    /**
     * Constructor
     *
     * @param cost the cost of the egg
     */
    public SellPterodactylEgg(int cost) {
        super(cost);
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return a message showing Pterodactyl egg was bought
     */
    @Override
    protected String boughtMessage() {
        return "Pterodactyl Egg bought!";
    }

    /**
     * Responsible for buying the item
     * @return the item bought
     */
    @Override
    protected Item bought() {
        return new PterodactylEgg();
    }

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return a message showing to buy Pterodactyl egg
     */
    @Override
    protected String sellMessage() {
        return "Buy Pterodactyl Egg";
    }

}
