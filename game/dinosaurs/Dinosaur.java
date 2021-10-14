package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.behaviours.Behaviour;
import game.dinosaurs.eggs.Egg;
import game.behaviours.*;

import java.util.ArrayList;
import java.util.List;

/**
 * the base class for dinosaurs
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 * @see Actor
 */
public abstract class Dinosaur extends Actor   {
    /**
     * The object is Gender
     */
    private Gender gender;
    /**
     * type as int, the death timer
     */
    protected int deathTimer = 0;
    /**
     * A new array list of type Behaviours is created
     */
    protected List<Behaviour> behaviours = new ArrayList<Behaviour>();
    /**
     * type as int, the lay egg timer
     */
    protected int layEggTimer=0;
    /**
     * type as int, the evolve timer
     */
    protected int evolveTimer=0;
    /**
     * The dinosaur has Egg
     */
    protected Egg egg;
    /**
     * type as int, the dinosaurID
     */
    private static int dinosaurId=1;
    /**
     * type as int, the maximum water level
     */
    private int maxWaterLevel;
    /**
     * type as int, the current water level
     */
    protected int waterLevel;

    /**
     * Constructor
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param gender the gender of the dinosaur
     */
    public Dinosaur(String name, char displayChar, int hitPoints, Gender gender,int waterLevel) {
        super(name, displayChar, hitPoints);
        this.name = name;
        this.gender = gender;
        this.maxWaterLevel=waterLevel;
        this.waterLevel=60;
        addCapability(gender);
        if(isBaby()) {
            setBaby();
        }
        else {
            setAdult();
        }
        nextID();
    }

    /**
     * Responsible to select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return do not perform any action if unconscious
     * @return the Action to be performed if possible
     * @return do not perform any action if no possible actions to perform
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        getTurn(map,display);
        if(hasCapability(DinosaurStatus.UNCONSCIOUS))
            return  new DoNothingAction();
        for (Behaviour behaviour : behaviours
        ) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Responsible to check whether can the dinosaur eat the object
     * @param location the Location where the location is to be added
     */
    public abstract boolean diet(Location location);

    /**
     * Responsible to find a partner
     * @param location the Location where the location is to be added
     * @return the partner found
     * @return null if partner not found
     */

    public Actor love(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            if (actor.toString().equalsIgnoreCase(toString()) && !actor.hasCapability(getGender()))
                return  actor;
        }
        return null;
    }
    /**
     * Responsible to get the gender of the dinosaur
     * @return the gender of the dinosaur
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Responsible to damage the food level and water level
     */
    private void damageOverTime() {
        if (isConscious()) {
            hurt(1);
        }
        reduceWaterLevel(1);

    }
    /**
     * Responsible to check whether can the dinosaur give birth
     */
    protected abstract boolean canBirth();
    /**
     * Responsible to get the egg
     */
    public abstract  Egg getEgg ();
    /**
     * Responsible to check whether is the dinosaur hungry
     */
    protected abstract boolean isHungry();
    /**
     * Responsible to check whether is the dinosaur starving to death
     */
    protected abstract boolean isStarveDeath();
    /**
     * Responsible to check whether the dinosaur wants to mate
     */
    protected abstract boolean isHorney();
    /**
     * Responsible to let the dinosaur evolve
     */
    protected abstract void canEvolve();


    /**
     * Responsible to check how long till the dinosaur can give birth
     */
    private void pregnancy() {
        if(hasCapability(DinosaurStatus.PREGNANT) && canBirth()){
            addCapability(DinosaurStatus.GIVEBIRTH);
            layEggTimer=0;
        }
    }

    /**
     * Responsible to remove the mating urge if the dinosaur is pregnant
     */
    private void horneySensor() {
        if (isHorney() && !hasCapability(DinosaurStatus.HORNEY) && !hasCapability(DinosaurStatus.PREGNANT)) {
            addCapability(DinosaurStatus.HORNEY);
        } else {
            removeCapability(DinosaurStatus.HORNEY);
        }
    }

    /**
     * Responsible to remove the mating urge
     */
    private void removeHorney(){
        if((!isHorney() && hasCapability(DinosaurStatus.HORNEY))|| hasCapability(DinosaurStatus.PREGNANT))
            removeCapability(DinosaurStatus.HORNEY);
    }

    /**
     * Responsible to tell the user that the dinosaur is hungry
     */
    private void hungrySensor(Location location,Display display) {
        if (isHungry() && !hasCapability(DinosaurStatus.HUNGRY)) {
            display.println(String.format("%s at (%s.%s) is getting hungry!", toString(), location.x(), location.y()));
            addCapability(DinosaurStatus.HUNGRY);
        }
    }

    /**
     * Responsible to remove the hunger urge
     */
    private void removeHunger(){
        if(!isHungry()){
            removeCapability(DinosaurStatus.HUNGRY);
        }
    }

    /**
     * checks if dinosaur is thirsty
     * @return true if its thirsty, false if is not
     */
    private boolean isThirsty(){
        if(waterLevel<40)
            return true;
        return false;
    }

    /**
     * Alerts game that the dinosaur is thirsty
     * @param location, the location of the dinosaur
     */
    private void thirstySensor(Location location,Display display){
        if (isThirsty() && !hasCapability(DinosaurStatus.THIRSTY)) {
            display.println(String.format("%s at (%s.%s) is getting thirsty!", toString(), location.x(), location.y()));
            addCapability(DinosaurStatus.THIRSTY);
        }
    }

    /**
     * removes THIRSTY capability of dinosaur
     */
    private void removeThirsty(){
        if(!isThirsty()){
            removeCapability(DinosaurStatus.THIRSTY);
        }
    }

    /**
     * reduces water level by amount
     * @param amount the amount to decrease water level
     */
    protected void reduceWaterLevel(int amount){
        waterLevel=Math.max(0,waterLevel-amount);
    }

    /**
     * increases water level by amount
     * @param amount the amount to increase water level
     */
    public void addWaterLevel(int amount){
        waterLevel=Math.min(maxWaterLevel,waterLevel+amount);
    }

    /**
     * checks if dinosaur is dehydrated
     * @return boolean, true if its dehydrated, false if its not
     */
    private boolean isDehydrated(){
        if(waterLevel==0)
            return true;
        return false;
    }

    /**
     * checks if dinosaur dies from thirst
     * @return boolean, true if dies from thirst and false if it does not
     */
    private boolean isThirstDeath(){
        if(hasCapability(DinosaurStatus.UNCONSCIOUS)&&hasCapability(DinosaurStatus.THIRSTY)&& deathTimer>=15)
            return true;
        return false;
    }

    /**
     * Waters the dinosaur if its watered
     */
    private void getWatered(){
        if(hasCapability(DinosaurStatus.WATERED)){
            addWaterLevel(10);
            removeCapability(DinosaurStatus.WATERED);
        }
    }

    /**
     * get the current water level of the dinosaur
     * @return an int, that represents the current water level of the dinosaur
     */
    public int getWaterLevel(){
        return waterLevel;
    }
    /**
     * Responsible to perform status effects on the current turn
     * @param map the map containing the Actor
     */
    private void getTurn(GameMap map,Display display) {
        getWatered();
        damageOverTime();
        removeHorney();
        horneySensor();
        removeHunger();
        hungrySensor(map.locationOf(this),display);
        removeThirsty();
        thirstySensor(map.locationOf(this),display);
        makeUnconscious();
        deathCounter();
        death();
        pregnancyCounter();
        pregnancy();
        evolveCounter();
        canEvolve();
    }

    /**
     * Responsible to make dinosaur unconscious if can
     */
    private void makeUnconscious( ) {
        if (!isConscious() || isDehydrated()) {
            addCapability(DinosaurStatus.UNCONSCIOUS);
        }
        else{
            removeCapability(DinosaurStatus.UNCONSCIOUS);
        }
    }

    /**
     * Responsible to increase deathTimer if dinosaur is unconscious else reset it to 0
     */
    private void deathCounter() {
        if (hasCapability(DinosaurStatus.UNCONSCIOUS)) {
            deathTimer += 1;
        } else {
            deathTimer = 0;
        }
    }

    /**
     * Responsible to increase the lay egg timer
     */
    private void pregnancyCounter(){
        if(hasCapability(DinosaurStatus.PREGNANT))
            layEggTimer+=1;
    }

    /**
     * Responsible to increase the evolve timer
     */
    private void evolveCounter(){
        if(hasCapability(DinosaurStatus.BABY))
            evolveTimer+=1;
    }

    /**
     * Responsible to make sure the dinosaur is dead if its starved or dehydrated for too long
     */
    private void death( ) {
        if (isStarveDeath()|| isThirstDeath() ) {
            addCapability(DinosaurStatus.DEAD);
            removeCapability(DinosaurStatus.ALIVE);
        }
    }

    /**
     * Responsible to make sure the dinosaur is a baby
     * @return true, if the dinosaur is a baby
     * @return false, if the dinosaur is not a baby
     */
    private boolean isBaby(){
       if( this.hasCapability(DinosaurStatus.BABY))
                return true;
       return false;
    }
    /**
     * Responsible get the nextID of the dinosaur
     */
    private void nextID(){
        dinosaurId+=1;
    }

    /**
     * Responsible get the ID of the dinosaur
     * @return the unique ID of the dinosaur
     */
    public int getId(){
        return  dinosaurId;
    }

    /**
     * Responsible set the dinosaur as a baby
     */
    protected void setBaby(){
        this.hitPoints=10;
        behaviours.add(new EatFoodBehaviour(this));
        behaviours.add(new SeekFoodBehaviour(this));
        behaviours.add(new DrinkWaterBehaviour(this));
        behaviours.add(new SeekLakeBehaviour(this));
        behaviours.add(new WanderBehaviour());
    }

    /**
     * Responsible set the dinosaur as an adult
     */
    protected void setAdult(){
        behaviours.removeAll(behaviours);
        behaviours.add(new GiveBirthBehaviour(this));
        behaviours.add(new MatingBehaviour(this));
        behaviours.add(new SeekMateBehaviour(this));
        behaviours.add(new EatFoodBehaviour(this));
        behaviours.add(new SeekFoodBehaviour(this));
        behaviours.add(new DrinkWaterBehaviour(this));
        behaviours.add(new SeekLakeBehaviour(this));
        behaviours.add(new WanderBehaviour());
        removeCapability(DinosaurStatus.BABY);
        evolveTimer=0;
    }


}
