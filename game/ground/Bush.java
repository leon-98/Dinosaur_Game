package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.EatAction;
import game.actions.SearchPlantAction;
import game.dinosaurs.DinosaurStatus;
import game.items.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for bush
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 * @see Ground
 * @see Plant
 */
public class Bush extends Ground  implements Plant {
    /**
     * A new array list of fruits of type Fruit
     */
    private List<Fruit> fruits= new ArrayList<>();
    /**
     * The object is allowableActions
     */
    private Actions allowableActions;
    /**
     * Constructor
     */
    public Bush() {
        super(',');
        addCapability(GroundStatus.ISBUSH);
        allowableActions= new Actions();
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     * @param location the Location where the location is to be added
     */
    public void tick(Location location){
        super.tick(location);
        allowableActions.clear();
        stepped(location);
        createFruits();
        hasFruits();
        if(location.containsAnActor()){
            Actor actor= location.getActor();
            if(actor.toString().equalsIgnoreCase("player")){
                if(fruits.size()>0){
                    allowableActions.add(new SearchPlantAction(this));
                }
            }
            else if(actor.hasCapability(DinosaurStatus.STEGOSAUR) && fruits.size()>0){
                Fruit fruit= loseFruit();
                allowableActions.add(new EatAction(fruit,fruit,actor));
            }
        }
    }

    /**
     * Responsible to create fruits
     */
    public void createFruits( ) {
        double stats;

        stats = 0.90;
        if (Math.random() > stats) {
            fruits.add(new Fruit());
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
     * Responsible to check whether the bush has fruit
     */
    private void hasFruits(){
        if (fruits.size()>0){
            addCapability(GroundStatus.BUSHHASFRUIT);
        }
        else{
            removeCapability(GroundStatus.BUSHHASFRUIT);
        }
    }

    /**
     * Responsible to change a bush to dirt if a dinosaur steps on it
     * @param location the Location where the location is to be added
     */
    private void stepped(Location location){
        double stat;
        stat=0.50;
        if(location.containsAnActor()){
            Actor actor= location.getActor();
            if(actor.toString().equalsIgnoreCase("Brachiosaur")){
                if(Math.random()>=stat){
                    addCapability(GroundStatus.STEPPED);
                }
            }
        }
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
}
