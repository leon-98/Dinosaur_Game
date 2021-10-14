package game;

import edu.monash.fit2099.engine.*;
import game.actions.ExitAction;

/**
 * This class is responsible for player
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see Actor
 */
public class Player extends Actor {
	/**
	 * A new menu created
	 */
	private Menu menu = new Menu();
	/**
	 * A new possibleActions created
	 */
	private Actions possibleActions= new Actions();
	private Actions someActions= new Actions();

	/**
	 * Constructor
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	/**
	 * Responsible to select and return an action to perform on the current turn.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn.
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		actions.add(new ExitAction());
		Location here= map.locationOf(this);
		possibleActions.clear();

		possibleActions.add(here.getGround().allowableActions(this,here,""));
		actions.add(someActions);
		actions.add(possibleActions);
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Responsible to get the unique ID of the player
	 * @return 0, ID of the player
	 */
	@Override
	public int getId() {
		return 0;
	}

	/**
	 * Responsible to get the health of the player
	 * @return the health of the player
	 */
	@Override
	public int getHp() {
		return hitPoints;
	}



}
