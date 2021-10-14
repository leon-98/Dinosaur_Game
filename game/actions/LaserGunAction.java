package game.actions;

import edu.monash.fit2099.engine.*;

/**
 * This class is responsible for laser gun action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class LaserGunAction extends Action {
    /**
     * type is NumberRange, has a range for x coordinates
     */
    private NumberRange xRange;
    /**
     * type is NumberRange, has a range for y coordinates
     */
    private NumberRange yRange;
    /**
     * type is int, has a x coordinates
     */
    private int hereX;
    /**
     * type is int, has a y coordinates
     */
    private int hereY;
    /**
     * Creates a new Menu called menu
     */
    private Menu menu= new Menu();
    /**
     * Creates a new Display called display
     */
    private Display display= new Display();
    /**
     * Creates a new Actions called actions
     */
    private Actions actions= new Actions();
    /**
     * type is str, a description to tell the user
     */
    private final String DESCRIPTION="START USING THE ALMIGHTY LASER GUN";

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return perform the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actions.clear();
        hereX=map.locationOf(actor).x();
        hereY=map.locationOf(actor).y();
        xRange=map.getXRange();
        yRange=map.getYRange();
        for (int x:xRange) {
            for (int y:yRange) {
                if(map.at(x,y).containsAnActor()){
                    Actor target=map.at(x,y).getActor();
                        if(hereX == x || hereY == y ){
                            if(!actor.toString().equals(target.toString()))
                            actions.add(new AttackAction(target));
                        }
                }
            }
        }

        actions.add(new DoNothingAction());
        System.out.println(actions.size());
        Action action = menu.showMenu(actor,actions,display);
        return action.execute(actor,map);
    }

    /**
     * Responsible to display a menu for the action
     * @param actor the Actor acting
     * @return the description to let the user know to use the laser gun
     */
    @Override
    public String menuDescription(Actor actor) {
        return DESCRIPTION;
    }
}
