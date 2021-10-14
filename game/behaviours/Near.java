package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for getting the nearest exit and nearest locations
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see ManhattanDistance
 */
public class Near implements  ManhattanDistance {

    /**
     * Responsible to get the nearest exit
     * @param there the Location where the there is to be added
     * @param exits array list of exits representing the exits of the map
     * @return null
     * @return the nearest direction found
     */
    public Exit getNearestExit(Location there, List<Exit> exits) {
        if (exits.size() == 0) {
            return null;
        }
        Exit nearestDirection = exits.get(0);
        int nearestDistance = distance(nearestDirection.getDestination(), there);
        for (int i = 1; i < exits.size(); i++) {
            Exit newDirection = exits.get(i);
            Location newDestination = newDirection.getDestination();
            int newDistance = distance(newDestination, there);
            if (nearestDistance > newDistance) {
                nearestDirection = newDirection;
                nearestDistance = newDistance;
            }
        }
        return nearestDirection;
    }

    /**
     * Responsible to get the nearest location
     * @param locations array list of locations representing the location of the map
     * @param here the Location where the here is to be added
     * @return the closest location
     */
    public Location getNearestLocation(ArrayList<Location> locations, Location here) {
        Location closest = locations.get(0);
        int closestDistance = distance(closest, here);
        for (int i = 1; i < locations.size(); i++) {
            int newDistance = distance(locations.get(i), here);
            if (newDistance < closestDistance) {
                closestDistance = newDistance;
                closest = locations.get(i);
            }
        }
        return closest;
    }
}
