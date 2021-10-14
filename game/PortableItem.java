package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public abstract class PortableItem extends Item  {

	/**
	 * Constructor
	 * @param name the name of the item
	 * @param displayChar the character representing the object
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
