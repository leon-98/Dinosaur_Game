package game.dinosaurs.eggs;

import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurStatus;
import game.dinosaurs.Pterodactyl;

/**
 * This class is responsible for the Egg of Pterodactyl
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Egg
 */
public class PterodactylEgg extends Egg{
    /**
     * Constructor
     *
     */
    public PterodactylEgg() {
        super("PterodactylEgg", 'p',100);
    }

    /**
     * get the dinosaur of Pterodactyl
     * @return a type Dinosaur, where the kind is Pterodactyl
     */
    @Override
    protected Dinosaur getDinosaur() {
        dinosaur=new Pterodactyl(getRandomGender());
        dinosaur.addCapability(DinosaurStatus.BABY);
        return dinosaur;
    }


}
