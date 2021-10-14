package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.LaserGunAction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for laser gun
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see WeaponItem
 */
public class LaserGun extends WeaponItem {
    /**
     * A new array list of actions of type Action
     */
    private List<Action> actions= new ArrayList<>();

    /**
     * Constructor
     */
    public LaserGun() {
        super("laser gun", ']', 100 , "shoots");
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param currentLocation the Location where the currentLocation is to be added
     * @param actor the Actor acting
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        actions.clear();


        actions.add(new LaserGunAction());

    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor
     * @return a collection of actions
     */
    @Override
    public List<Action> getAllowableActions() {
        return actions;
    }
}
