package game.actions;

import edu.monash.fit2099.engine.*;
import game.FoodSource;

/**
 * This class is responsible for bite action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class BiteAction extends AttackAction {
    /**
     * The object that is treated as a food source
     */

    /**
     * Constructor
     * @param target the Actor to attack
     */
    public BiteAction(Actor target ) {
        super(target);
    }

    /**
     * Responsible to process the damage made to the target with a weapon
     * @param self the Actor acting
     * @param target the target getting attacked
     * @param weapon the weapon used to attack the target
     * @return the actor attacks the target with a weapon
     */
    @Override
    protected String processDamage(Actor self, Actor target, Weapon weapon) {
        self.heal(weapon.damage()); // heals back the amount based on foodSource value
        return super.processDamage(self, target, weapon);
    }
}
