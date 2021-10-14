package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * This class is responsible for bush
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see Ground
 */
public class Dirt extends Ground {
	/**
	 * Constructor
	 */
	public Dirt() {
		super('.');
		addCapability(GroundStatus.ISDIRT);
	}
	/**
	 * Called once per turn, so that Locations can experience the passage time. If that's
	 * important to them.
	 * @param location the Location where the location is to be added
	 */
	public void tick(Location location) {
		super.tick(location);
	}
}
