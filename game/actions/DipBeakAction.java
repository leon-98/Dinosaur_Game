package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
import game.ground.Fish;

import java.util.ArrayList;
/**
 * This class is responsible for dipping beak action
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class DipBeakAction extends Action {
    /**
     * An arraylist of type Fish where fishes are the  fish that the dinosaur caught
     */
    private ArrayList<Fish> fishes;

    /**
     * constructor for DipBeakAction
     * @param fishes , the fishes that the dinosaur caught
     */
    public DipBeakAction( ArrayList<Fish> fishes) {
        this.fishes = fishes;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String string="";
        //eats fishes if can
        if (!fishes.isEmpty()){
            Action action= new EatAction(fishes.get(0),actor);
            string+="\n"+action.execute(actor,map);
            fishes.remove(0);
            getNextAction().execute(actor,map);
        }
        return String.format("%s dips beak%s ",actor.toString(),string);
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    @Override
    public Action getNextAction() {
        return this;
    }

}
