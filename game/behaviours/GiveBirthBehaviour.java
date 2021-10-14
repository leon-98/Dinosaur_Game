package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.LayEggAction;

import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;

/**
 * This class is responsible for give birth behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 */
public class GiveBirthBehaviour implements Behaviour {
    /**
     * The Dinosaur is self
     */
    private Dinosaur self;

    /**
     * Constructor
     * @param self the dinosaur itself
     */
    public GiveBirthBehaviour(Dinosaur self) {
        this.self = self;
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return get birth action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return getBirth();
    }

    /**
     * Responsible to give birth
     * @return lay egg action
     * @return null if cant lay egg
     */
    private Action getBirth(){
        if(self.hasCapability(DinosaurStatus.GIVEBIRTH))
            return new LayEggAction(self);
        return null;
    }
}
