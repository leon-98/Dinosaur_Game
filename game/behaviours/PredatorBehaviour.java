package game.behaviours;

import edu.monash.fit2099.engine.*;

import game.dinosaurs.Predator;

/**
 * This class is responsible for predator behaviour
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Behaviour
 */
public class PredatorBehaviour implements Behaviour {
    /**
     * The Predator is self
     */
    private Predator self;
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Constructor
     * @param self the dinosaur is a carnivore
     */
    public PredatorBehaviour(Predator self) {
        this.self = self;
    }

    /**
     * Responsible to get the action
     * @param actor the Actor acting
     * @param map the map that contains this location
     * @return allowable action to its prey if cabaple to hunt it
     * @return null if cannot do so
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here= map.locationOf(actor);
        for (Exit direction:here.getExits()) {
            Location destination=direction.getDestination();
            if(destination.containsAnActor()){
                target=destination.getActor();
                if(self.canEatPrey(target)){
                    self.rememberPrey(actor.getId());
                    if(target.getAllowableActions(target, "", map).size()>0)
                            return target.getAllowableActions(target, "", map).get(0);
                        }
            }
        }
        return null;
    }

}
