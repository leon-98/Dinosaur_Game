package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.FoodType;
import game.actions.SwallowAction;
import game.behaviours.DipBeakBehaviour;
import game.behaviours.LandAndEatBehaviour;
import game.behaviours.SeekTreeBehaviour;
import game.dinosaurs.eggs.Egg;
import game.dinosaurs.eggs.PterodactylEgg;
import game.ground.GroundStatus;
import game.ground.LakeStatus;

import java.util.List;
/**
 * This class is responsible for the Pterodactyl
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Dinosaur
 */
public class Pterodactyl extends Dinosaur implements EatFloorItems{
    /**
     * type as int, the starting health of the Pterodactyl
     */
    private final int STARTING_HP = 60;
    /**
     * type as int, the hunger level of the Pterodactyl
     */
    private final int HUNGER_LEVEL = 90;
    /**
     * type as int, the horney level of the Pterodactyl
     */
    private final int HORNEY_LEVEL = 50;
    /**
     * type as int, the evolve level of the Pterodactyl
     */
    private final int EVOLVE_LEVEL = 30;
    /**
     * type as int, the unconscious level of the Pterodactyl
     */
    private final int UNCONSCIOUS_LEVEL = 20;
    /**
     * type as int, the lay egg level of the Pterodactyl
     */
    private final int LAY_EGG_LEVEL = 10;
    /**
     * A new pterodactylEgg of type Egg is created
     */
    private Egg pterodactylEgg = new PterodactylEgg();
    /**
     *
     */
    private final int MAX_FLY_FUEL_LEVEL=30;

    private int flyFuel=30;

    private Location previousLocation=null;
    /**
     * Constructor of Pterodactyl
     *
     * @param gender      the gender of the dinosaur
     */
    public Pterodactyl( Gender gender) {
        super("Pterodactyl", 'P', 100, gender,100);
        behaviours.remove(3);
        behaviours.add(3, new LandAndEatBehaviour(this,this));
        behaviours.remove(5);
        behaviours.add(3,new DipBeakBehaviour(this));
        behaviours.add(3, new SeekTreeBehaviour(this));
        addCapability(DinosaurStatus.CARNIVORE);
        addCapability(DinosaurStatus.PTERODACTYL);
        addCapability(DinosaurStatus.PREY);
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Location here=map.locationOf(this);
        // checks to see if its on tree and let it know its on tree
        if(here.getGround().hasCapability(GroundStatus.ISTREE)) {
            addCapability(DinosaurStatus.ISONTREE);
            removeCapability(DinosaurStatus.ISFLYING);
            flyFuel=MAX_FLY_FUEL_LEVEL;
        }
        else{
            // remove status ISONTREE if no longer on tree
            this.removeCapability(DinosaurStatus.ISONTREE);
            // give ISFLYING if its not already flying
            if(!this.hasCapability(DinosaurStatus.ISFLYING)){
                addCapability(DinosaurStatus.ISFLYING);
                display.println(toString()+" is flying");
            }
            // reduce flying fuel if possible
            if(!here.equals(previousLocation)&&hasCapability(DinosaurStatus.ISFLYING)) {
                previousLocation = map.locationOf(this);
                flyFuel =Math.max(0,flyFuel-1);
            }

        }
        //  remove flying capability if can no longer fly
       if(flyFuel==0 && !here.getGround().hasCapability(LakeStatus.ISLAKE)) {
           removeCapability(DinosaurStatus.ISFLYING);
           display.println(toString()+" has landed on the ground");
       }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Responsible to check whether can the dinosaur eat the object
     * @param location the Location where the foodsource is
     * @return true, if the object is edible
     * @return true, if the plant is edible
     * @return false, the object and plant is not edible
     */
    @Override
    public boolean diet(Location location) {
        if(checkEatObjects(location.getItems()))
        return true;
        if(location.getGround().hasCapability(LakeStatus.LAKEHASFISH)&&hasCapability(DinosaurStatus.ISFLYING))
            return true;
        return false;
    }

    /**
     * Responsible to check whether can the dinosaur give birth
     * @return true, if the dinosaur can give birth
     * @return false, if the dinosaur cannot give birth
     */
    @Override
    protected boolean canBirth() {
        if (layEggTimer >= LAY_EGG_LEVEL && this.hasCapability(DinosaurStatus.ISONTREE))
            return true;
        return false;
    }

    /**
     * get the egg of this dinosaur
     * @return the egg of Pterodactyl
     */
    @Override
    public Egg getEgg() {
        return pterodactylEgg;
    }

    /**
     * Responsible to check whether is the dinosaur hungry
     * @return true, if the dinosaur is hungry
     * @return false, if the dinosaur is not hungry
     */
    @Override
    protected boolean isHungry() {
        if (hitPoints < HUNGER_LEVEL)
            return true;
        return false;
    }
    /**
     * Responsible to check whether is the dinosaur starving to death
     * @return true, if the dinosaur is unconscious
     * @return false, if the dinosaur is conscious
     */
    @Override
    protected boolean isStarveDeath() {
        if (deathTimer == UNCONSCIOUS_LEVEL)
            return true;
        return false;
    }

    /**
     * Responsible to check whether the dinosaur wants to mate
     * @return true, if the dinosaur wants to mate
     * @return false, if the dinosaur does not wants to mate
     */
    @Override
    protected boolean isHorney() {
        if (hitPoints > HORNEY_LEVEL) {
            return true;
        }
        return false;
    }

    /**
     * Responsible to check whether can the dinosaur evolve
     */
    @Override
    protected void canEvolve() {
        if (evolveTimer == EVOLVE_LEVEL)
            setAdult();
    }

    /**
     * get the hitpoints of the dinosaur
     * @return the hitPoints of the dinosaur
     */
    @Override
    public int getHp() {
        return hitPoints;
    }

    /**
     * Responsible to evolve the dinosaur
     */
    @Override
    protected void setAdult() {
        super.setAdult();
        this.hitPoints = STARTING_HP;
    }

    @Override
    public Actor love(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            // only allows to mate if they are on tree
            if (actor.hasCapability(DinosaurStatus.ISONTREE) && this.hasCapability(DinosaurStatus.ISONTREE)) {
                if (actor.toString().equalsIgnoreCase(toString()) && !actor.hasCapability(getGender()))
                    return actor;
            }
        }
        return null;
    }
    /**
     * Responsible to check whether the dinosaur can eat the item
     * @param item a physical object in the game world
     * @return true, if the dinosaur can eat the object
     * @return false, if the dinosaur can not eat the object
     */
    @Override
    public boolean canEatObject(Item item) {
        if (item.hasCapability(FoodType.CORPSE))
            return true;
        return false;
    }

    /**
     * Responsible to check whether the dinosaur is able to eat any of the objects
     * @param items an array list of a physical object in the game world
     * @return true, if the dinosaur is able to eat the object
     * @return false, if the dinosaur is not able to eat the object
     */
    @Override
    public boolean checkEatObjects(List<Item> items) {
        for (Item item : items) {
            if (canEatObject(item))
                return  true;
        }
        return false;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        // if its not on tree and its not flying allow swallow action
        if(!this.hasCapability(DinosaurStatus.ISONTREE) && ! this.hasCapability(DinosaurStatus.ISFLYING))
            return new Actions(new SwallowAction(this));
        return super.getAllowableActions(otherActor, direction, map);
    }
}
