package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameStatus;
import game.JurassicWorld;

import java.util.Scanner;
/**
 * This class is responsible for setting up challenge mode
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see Action
 */
public class ChallengeAction extends Action {

    private JurassicWorld jurassicWorld;
    private Scanner scanner= new Scanner(System.in);

    /**
     * constructor for ChallengeAction
     * @param jurassicWorld, the jurassic world
     */
    public ChallengeAction(JurassicWorld jurassicWorld) {
        this.jurassicWorld = jurassicWorld;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.print("Enter moves limit: ");
        jurassicWorld.setMoveLimit(scanner.nextInt());
        System.out.print("Enter Eco Points goal: ");
        jurassicWorld.setPointsGoal(scanner.nextInt());
        actor.addCapability(GameStatus.CHALLENGE);
        return "Challenge Mode Selected";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Select Challenge Mode";
    }
}
