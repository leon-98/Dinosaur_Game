package game;

import edu.monash.fit2099.engine.*;
import game.actions.ChallengeAction;
import game.actions.LeaveAction;
import game.actions.SandBoxAction;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Gender;
import game.dinosaurs.Pterodactyl;
import game.dinosaurs.Stegosaur;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the jurassic world
 * @author Leon
 * @version 1.0.0
 * @since 17/5/2021
 * @see World
 */
public class JurassicWorld extends World {
    /**
     * the amount of moves it can make
     */
    private int moves = 0;
    /**
     * the max moves it can make
     */
    private int moveLimit;
    /**
     * the eco points goal
     */
    private int pointsGoal;
    /**
     * a menu at the start of the game
     */
    private Menu gameMenu = new Menu();
    /**
     * actions for Game menu which will show at the start of the game
     */
    private Actions gameActions = new Actions();
    private ArrayList<List<String>> maps;
    private GroundFactory fancyGroundFactory;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public JurassicWorld(Display display,ArrayList<List<String>> maps,GroundFactory fancyGroundFactory) {
        super(display);
        this.maps=maps;
        this.fancyGroundFactory= fancyGroundFactory;
        // add the game menu actions into gameMenu
        gameActions.add(new ChallengeAction(this));
        gameActions.add(new SandBoxAction());
        gameActions.add(new LeaveAction());
        reset();
    }

    @Override
    protected boolean stillRunning() {

        if (player.hasCapability(GameStatus.LEAVEGAME)||player.hasCapability(GameStatus.EXITED) || player.hasCapability(GameStatus.LOST) || player.hasCapability(GameStatus.WON))
        {
            return false;
        }
        return true;
    }

    @Override
    public void run() {
            Location here = actorLocations.locationOf(player);
            while (true) {
                gameMenu.showMenu(player, gameActions, display).execute(player, here.map());
                if(player.hasCapability(GameStatus.LEAVEGAME))
                    break;
                super.run();
                reset();
            }

    }

    /**
     * resets the game
     */
    private void reset(){
        gameMaps.clear();
        actorLocations= new ActorLocations();
        moves=0;
        for (int i = 0; i < maps.size(); i++) {
            GameMap gameMap=new JurassicMap(fancyGroundFactory, maps.get(i));
            addGameMap( gameMap);
            if(i==0){
                //adds player
                Actor player = new Player("Player", '@', 100);
                addPlayer(player, gameMap.at(9, 1));
                //add stegosaurs
                gameMap.at(30, 12).addActor(new Stegosaur(Gender.MALE));
                gameMap.at(31, 12).addActor(new Stegosaur(Gender.FEMALE));
                // add pterodactyl
                gameMap.at(38, 12).addActor(new Pterodactyl(Gender.MALE));
                gameMap.at(39, 12).addActor(new Pterodactyl(Gender.FEMALE));

                // Place a pair of brachiosaur in the middle of the map
                gameMap.at(55, 12).addActor(new Brachiosaur(Gender.MALE));
                gameMap.at(57, 12).addActor(new Brachiosaur(Gender.FEMALE));
            }
        }
}
    /**
     * increment moves
     */
    private void incrementMoves() {
        moves += 1;
    }

    /**
     * set the move limit by amount
     * @param amount, an int that represents the amount for moveLimit
     */
    public void setMoveLimit(int amount) {
        moveLimit = amount;
    }

    /**
     * set the points goal by amount
     * @param amount, an int that represents the amount for pointsGoal
     */
    public void setPointsGoal(int amount) {
        this.pointsGoal = amount;
    }


    /**
     * make the decision if player wins or not
     */
    private void makeDecision() {
        if (moves<=moveLimit && checkPointsGoal()) {
            player.addCapability(GameStatus.WON);
        }
        else if(moves==moveLimit && !checkPointsGoal()){
            player.addCapability(GameStatus.LOST);
        }


    }

    /**
     * check if reach eco Points goal
     * @return true if reach eco points goal else false
     */
    private boolean checkPointsGoal() {
        if (EcoPoints.getPoints() >= pointsGoal)
            return true;
        return false;
    }

    @Override
    protected void processActorTurn(Actor actor) {
        moveNextMap();
        super.processActorTurn(actor);
        // if its player and is in challenge mode, increment moves ands checks rather if win or lose
        if (actor.toString().equalsIgnoreCase(player.toString()) && player.hasCapability(GameStatus.CHALLENGE)) {
            incrementMoves();
            makeDecision();
            display.println(String.format("Moves/MovesLimit: %s/%s EcoPoints/EcoPointsGoal: %s/%s", moves, moveLimit, EcoPoints.getPoints(),pointsGoal));
        }
    }


    @Override
    protected String endGameMessage() {
        if (player.hasCapability(GameStatus.EXITED))
            return "Player exits the game";
        if (player.hasCapability(GameStatus.WON))
            return "Player Won the game";
        if (player.hasCapability(GameStatus.LOST))
            return "Player Lost the game";
        return super.endGameMessage();
    }

    /**
     * gives player the option to move to next map if can
     */
    private void moveNextMap(){
        GameMap mapWithPlayer=null;
        GameMap mapWithoutPlayer=null;
        for (GameMap map : gameMaps) {
            if(map.contains(player)) {
                    mapWithPlayer=map;
            }
            else{
                mapWithoutPlayer=map;
            }
        }
        Location here= mapWithPlayer.locationOf(player);
        if(here.y()==mapWithPlayer.getYRange().min()){
            int y=mapWithoutPlayer.getYRange().max();
            int x = here.x();
            mapWithPlayer.moveActor(player,mapWithoutPlayer.at(x,y));

        }
        else if(here.y()==mapWithPlayer.getYRange().max()){
            int y=mapWithoutPlayer.getYRange().min();
            int x = here.x();
            mapWithPlayer.moveActor(player,mapWithoutPlayer.at(x,y));

        }
    }
}
