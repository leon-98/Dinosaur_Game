package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.dinosaurs.Gender;

/**
 * This class is responsible for mate action
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Action
 */
public class MateAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;
    /**
     * The Actor that is a male
     */
    private Actor male;
    /**
     * The Actor that is a female
     */
    private Actor female;
    /**
     * The Actor is self
     */
    private Actor self;

    /**
     * Constructor
     * @param target the target hat is to be attacked
     * @param self the actor is self
     */
    public MateAction(Actor target,Actor self) {
        this.target = target;
        this.self=self;
    }

    /**
     * Responsible to perform the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return result of pairing dinosaurs to mate at certain location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
         classifier();
         mating();
        int x = map.locationOf(actor).x();
        int y = map.locationOf(actor).y();

        return String.format("A pair of %s mated at around (%s,%s)",actor.toString(),x,y);
    }

    /**
     * Responsible to display a menu for the action
     * @param actor the Actor acting
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    /**
     * Responsible to check whether the dinosaur is a female
     * @return true, if its a female
     * @return false, if its not a female
     */
private boolean isFemale(){
        if(target.hasCapability(Gender.FEMALE))
            return true;
        return false;
}
    /**
     * Responsible to classify the gender of the dinosaurs
     */
private void classifier() {
    if (isFemale()) {
        female = target;
        male = self;
    }
    else{
        female=self;
        male=target;
    }
}
    /**
     * Responsible to let the dinosaurs mate
     */
private void mating(){
        female.addCapability(DinosaurStatus.PREGNANT);
}

}
