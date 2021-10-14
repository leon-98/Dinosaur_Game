package game;

import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

/**
 * This class is responsible for JurassicMap
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see GameMap
 */
public class JurassicMap extends GameMap  {

    /**
     * Constructor
     * @param groundFactory to create new map locations
     * @param lines List of Strings representing rows of the map
     */
    public JurassicMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    /**
     * Responsible to create new locations
     * @param x the x coordinate
     * @param y the y coordinate
     * @return create a new location on the map
     */
    @Override
    protected Location makeNewLocation(int x, int y) {
        return new JurassicLocation(this, x, y,groundFactory);
    }


}

