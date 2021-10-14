package game;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.DinosaurStatus;
import game.ground.Bush;
import game.ground.LakeStatus;
import game.ground.GroundStatus;
import game.items.corpses.*;

/**
 * This class is responsible for JurassicLocation
 * @author Yi Sen
 * @version 1.0.0
 * @since 5/5/2021
 */
public class JurassicLocation extends Location {
    /**
     * A new brachiosaurCorpse created
     */
    protected Corpse brachiosaurCorpse= new BrachiosaurCorpse();
    /**
     * A new allosaurCorpse created
     */
    protected  Corpse allosaurCorpse= new AllosaurCorpse();
    /**
     * A new stegosaurCorpse created
     */
    protected Corpse stegosaurCorpse= new StegosaurCorpse();
    /**
     * A new pterodactylCorpse created
     */
    protected Corpse pterodactylCorpse= new PterodactylCorpse();
    private int turn = 0;
    private GroundFactory groundFactory;

    /**
     * Constructor
     * @param map the map that contains this location
     * @param x contains the x coordinate
     * @param y contains the y coordinate
     */
    public JurassicLocation(GameMap map, int x, int y,GroundFactory groundFactory) {
        super(map, x, y);
        this.groundFactory=groundFactory;
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them
     */
    public void tick() {
        turn++;
        if (turn == 10) {
            turn = 0;
            double stat = 0.80;
            if (Math.random() > stat) {
                addWater();
                if(containsAnActor()){
                    if(!getActor().toString().equalsIgnoreCase("player"))
                        getActor().addCapability(DinosaurStatus.WATERED);
                }
            }
        }
        spawnBushes();
        getDead();
        removeBush();
        super.tick();
    }

    /**
     * Responsible to spawn bushes on the map
     */
    public void spawnBushes() {
        boolean flag = true;
        int countBush = 0;
        double stats;
        Ground ground= getGround();

        if (!ground.hasCapability(GroundStatus.ISDIRT)) {
            flag = false;
        }
        for (Exit direction:getExits() ) {
            ground = direction.getDestination().getGround();

            if (ground.hasCapability(LakeStatus.ISLAKE)) {
                flag = false;
            }
            if (ground.hasCapability(GroundStatus.ISTREE)) {
                flag = false;
            }
            if (ground.hasCapability(GroundStatus.ISBUSH)) {
                countBush += 1;
            }
        }
        if (countBush >= 2) {
            stats = 0.90;
        } else {
            stats = 0.99;
        }
        if (Math.random() > stats && flag == true) {
            setGround(groundFactory.newGround(','));
        }
    }

    /**
     * Responsible to remove bushes on the map
     */
    private void removeBush(){
        if(getGround().hasCapability(GroundStatus.STEPPED)){
            setGround(groundFactory.newGround('.'));
        }
    }

    /**
     * Responsible to get the corpse of the dinosaur
     * @param name name of the dinosaur corpse
     * @return stegosaur corpse
     * @return brachiosaur corpse
     * @return allosaur corpse
     * @return pterodactyl corpse
     * @return null if no corpse
     */
    private Corpse getCorpse(String name){
        if(name.equalsIgnoreCase("Stegosaur") ){
            return stegosaurCorpse;
        }
        else if (name.equalsIgnoreCase("Brachiosaur") ){
            return brachiosaurCorpse;
        }
        else if ( name.equalsIgnoreCase("Allosaur") ){
            return  allosaurCorpse;
        }
        else if ( name.equalsIgnoreCase("Pterodactyl") ){
            return  pterodactylCorpse;
        }
        return null;
    }

    /**
     * Responsible to get dead dinosaurs
     */
    private void getDead(){
        if(containsAnActor()){
            Actor actor= getActor();
            if(actor.hasCapability(DinosaurStatus.DEAD)){
                addItem(getCorpse(actor.toString()));
                Actions dropActions = new Actions();
                for (Item item : actor.getInventory())
                    dropActions.add(item.getDropAction());
                for (Action drop : dropActions)
                    drop.execute(actor, map());
                map().removeActor(actor);
            }
        }
    }

    /**
     * Responsible to add water to the lake
     */
    public void addWater(){
        Ground ground = getGround();
        if (ground.hasCapability(LakeStatus.ISLAKE)) {
            ground.addCapability(LakeStatus.ADDWATER);
        }
    }
}
