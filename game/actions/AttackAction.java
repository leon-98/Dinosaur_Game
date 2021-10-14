package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for special actions for attacking other actors
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	/**
	 * Responsible to perform the action
	 * @param actor the Actor acting
	 * @param map the map that contains this location
	 * @return result of what happened to the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		String result =processDamage(actor,target,weapon);
		if (!target.isConscious()) {
			target.addCapability(DinosaurStatus.DEAD); // they are now dead
			

			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * Responsible to display a menu for the action
	 * @param actor the Actor acting
	 * @return the actor attacks the target
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}

	/**
	 * Responsible to process the damage made to the target with a weapon
	 * @param self the Actor acting
	 * @param target the target getting attacked
	 * @param weapon the weapon used to attack the target
	 * @return the actor attacks the target with a weapon
	 */
protected String processDamage(Actor self,Actor target ,Weapon weapon){
	int damage = weapon.damage();
		target.hurt(damage);
	return self + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
}

}
