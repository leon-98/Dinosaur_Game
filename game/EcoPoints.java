package game;

/**
 * This class is responsible allowing a point system
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 */
public class EcoPoints    {
    /**
     *type is int, ecoPoints is made static to allow it to be an asset of the EcoPoints class, which let us store points.
     */
    private static int ecoPoints=0;

    /**
     * increments ecoPoints based on the parameter's value
     * @param points the amount of points to add on ecoPoints, type is int
     */
    public  static void addPoints(int points) {
        ecoPoints+=points;
    }

    /**
     * decrement ecoPoints based on the parameter's value
     * @param points the amount of points to reduce on ecoPoints, type is int
     */
    public  static void reducePoints(int points) {
            ecoPoints-=points;
    }

    /**
     * get the accumulated ecoPoints value
     * @return an int, that represents the acummulated ecoPoints value
     */
    public static int getPoints() {
        return ecoPoints;
    }
}
