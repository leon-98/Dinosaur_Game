package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for seeking behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 * @see ManhattanDistance
 */
public abstract class SeekBehaviour implements   Behaviour,ManhattanDistance{
    /**
     * A new Near is created
     */
    private Near near= new Near();
    /**
     * The Dinosaur is self
     */
    protected Dinosaur self;

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public SeekBehaviour(Dinosaur self) {
        this.self = self;
    }

    /**
     * Responsible to get the nearest exit
     * @param there the Location where the there is to be added
     * @param exits array list of exits representing the exits of the map
     * @return the nearest direction found
     */
    protected Exit nearestExit(Location there, List<Exit> exits){
        return near.getNearestExit(there,exits);
    }

    /**
     * Responsible to get the nearest location
     * @param locations array list of locations representing the location of the map
     * @param here the Location where the here is to be added
     * @return the closest location
     */
    protected Location nearestLocation(ArrayList<Location> locations, Location here) {
        return near.getNearestLocation(locations,here);
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return null if no locations to go to
     * @return null if no directions to go to
     * @return null if nearest dirction is null
     * @return the actor can move towards that location
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(self);
        ArrayList<Location> places = getGoalLocations(getLocations(map));
        if (!gotLocations(places)) {
            return null;
        }
        Location there = nearestLocation(places, here);
        int currentDistance = distance(here, there);
        List<Exit> directions = availableExits(here.getExits(), actor);
        if (directions == null) {

            return null;
        }
        Exit direction = nearestExit(there, directions);
        if (direction == null) {
            return null;
        }

        Location destination=direction.getDestination();
        int newDistance= distance(destination,there);
        return shouldMove(newDistance,currentDistance,destination,direction);
    }

    /**
     * Responsible to get the available exit
     * @param exits array list of exits representing the exits of the map
     * @param actor the Actor acting
     * @return null if no direction found
     * @return the direction found
     */
    protected List<Exit> availableExits(List<Exit> exits, Actor actor) {
        List<Exit> directions = new ArrayList<Exit>();
        if (exits.size() == 0) {
            return null;
        }
        for (Exit direction : exits) {
            Location destination = direction.getDestination();
            if (destination.canActorEnter(actor)) {
                directions.add(direction);
            }
        }
        return directions;
    }

    /**
     * Responsible to get the goal location
     * @param locations array list of locations, contain location
     * @return the goal location
     */
    protected ArrayList<Location> getGoalLocations(ArrayList<Location> locations) {
        ArrayList<Location> goalLocation = new ArrayList<Location>();
        for (Location location : locations
        ) {
            if (goal(location)) {
                goalLocation.add(location);
            }
        }
        return goalLocation;
    }
    /**
     * Responsible to get the goal
     * @param location the Location where the location is to be added
     */
    protected abstract boolean goal(Location location);

    /**
     * Responsible to get the location
     * @param map the map that contains this location
     * @return the location
     */
    protected ArrayList<Location> getLocations(GameMap map) {
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int x = 0; x < map.getXRange().max(); x++) {
            for (int y = 0; y < map.getYRange().max(); y++) {
                locations.add(map.at(x, y));
            }
        }
        return locations;
    }

    /**
     * Responsible to check whether the location exists
     * @param locations array list of locations, contain location
     * @return false, if location not found
     * @return true, if location is found
     */
    protected boolean gotLocations(ArrayList<Location> locations) {
        if (locations.isEmpty())
            return false;
        return true;
    }

    /**
     * Responsible to move the actor
     * @param newDistance a new distance
     * @param direction the direction to head towards
     * @param destination the new destination in the map
     * @param currentDistance the current distance
     * @return a new location to move to
     * @return null if no location to move to
     */
protected MoveActorAction shouldMove(int newDistance, int currentDistance,Location destination,Exit direction){
    if(newDistance<=currentDistance) {
        return new MoveActorAction(destination, direction.getName());
    }
    return null;
}

}
