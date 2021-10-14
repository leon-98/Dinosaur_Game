package game.actions.sellableactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.EcoPoints;

/**
 * This class is responsible of sell action for vending machine
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public abstract class SellAction extends Action {

    /**
     * type is int, cost of the eco points
     */
    private int cost;

    /**
     * Constructor
     * @param cost
     */
    public SellAction(int cost) {
        this.cost = cost;
    }

    /**
     * Responsible for displaying a bought message when the user buys something
     * @return String, return bought message
     */
    protected abstract String boughtMessage();

    /**
     * Responsible for buying the item
     * @return Item, which is the item bought
     */
    protected abstract Item bought();

    /**
     * Responsible for displaying a sell message to user to know what to buy
     * @return String, return sell message
     */
    protected abstract String sellMessage();

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return the bought message when player buys something
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(bought());
        EcoPoints.reducePoints(cost);
        return boughtMessage();
    }

    /**
     * Responsible to display a menu for the vending machine
     * @param actor the Actor acting
     * @return the sell message to let player know the selling items
     */
    @Override
    public String menuDescription(Actor actor) {
        return sellMessage();
    }
}
