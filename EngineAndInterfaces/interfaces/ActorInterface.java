package edu.monash.fit2099.interfaces;


/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 */
public interface ActorInterface {
    /**
     * Responsible to get the unique ID
     */
int getId();
    /**
     * Responsible to get the health
     */
int getHp();


}
