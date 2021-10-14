package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.actions.EatAction;
import game.actions.SearchPlantAction;
import game.dinosaurs.DinosaurStatus;
import game.items.Fruit;

import java.util.ArrayList;

/**
 * This class is responsible for tree
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see Ground
 * @see Plant
 */
public class Tree extends Ground implements Plant {
	/**
	 * type int, age of the tree
	 */
	private int age = 0;
	/**
	 * A new array list of fruits of type Fruit
	 */
	private ArrayList<Fruit> fruits = new ArrayList<>();
	/**
	 * The object is allowableActions
	 */
	private Actions allowableActions;

	/**
	 * Constructor
	 */
	public Tree() {
		super('+');
		addCapability(GroundStatus.ISTREE);
		allowableActions= new Actions();
	}

	/**
	 * Called once per turn, so that Locations can experience the passage time. If that's
	 * important to them.
	 * @param location the Location where the location is to be added
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
allowableActions.clear();
		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
		createFruits();
		dropFruits(location);
		hasFruits();
		if(location.containsAnActor()){
			Actor actor=location.getActor();
			if(actor.toString().equalsIgnoreCase("player")){
				if(fruits.size()>0){
					allowableActions.add(new SearchPlantAction(this));
				}
			}
			else if (actor.hasCapability(DinosaurStatus.BRACHIOSAUR)){
				Fruit fruit= new Fruit(loseAllFruits());
				allowableActions.add(new EatAction(fruit,actor));
			}

		}
	}

	/**
	 * Responsible to create fruits
	 */
	@Override
	public void createFruits() {
		double stats;
		stats = 0.80;
		if (Math.random() > stats) {
			fruits.add(new Fruit());
			EcoPoints.addPoints(1);
		}
	}

	/**
	 * Responsible to lose fruits
	 * @return fruit
	 */
	@Override
	public Fruit loseFruit() {
		Fruit fruit=fruits.get(0);
		fruits.remove(fruit);
		return fruit;
	}

	/**
	 * Responsible to check whether the tree has fruit
	 */
	private void hasFruits(){
		if (fruits.size()>0){
			addCapability(GroundStatus.TREEHASFRUIT);
		}
		else{
			removeCapability(GroundStatus.TREEHASFRUIT);
		}
	}

	/**
	 * Responsible to drop the fruit from the tree
	 * @param location the Location where the location is to be added
	 */
	public void dropFruits(Location location){
		double drop;
		for (int i = 0; i < fruits.size(); i++){
			drop = 0.95;
			if (Math.random() >= drop) {
				Fruit fruit = fruits.get(i);
				location.addItem(fruit);
				removeFruits();
			}
		}
	}

	/**
	 * Responsible to remove the fruit
	 */
	public void removeFruits(){
		fruits.remove(0);
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param actor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param location   the Location where the location is to be added
	 * @return A collection of actions.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {

		return allowableActions;
	}

	/**
	 * Responsible to lose all the fruit
	 * @return amount of fruit
	 */
	public int loseAllFruits( ) {
		int amount=0;
		for (int i = 0; i <fruits.size() ; i++) {
			amount+=5;
		}
		if(fruits.size()!=0)
			fruits.removeAll(fruits);
		return amount;
	}
}
