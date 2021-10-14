package game.dinosaurs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible locate a prey and decrement the cool down time for the prey
 * @author Leon
 * @version 1.0.0
 * @since 5/5/2021
 */
public class PreyManager {
    /**
     * the object is HashMap, which takes in two int values
     */
    private HashMap<Integer,Integer> hashMap;
    /**
     * A new array list of type Integer is created
     */
    private ArrayList<Integer> dinosaurIds = new ArrayList<>();
    /**
     * type as int, the cool down time for the prey
     */
    private final int CANT_ATTACK_TURNS=20;

    /**
     * Constructor
     */
    public PreyManager() {
        hashMap= new HashMap<Integer, Integer>();
    }

    /**
     * Responsible to add a prey
     * @param dinosaurId the unique dinosaur ID
     */
    public void addPrey(int dinosaurId){
        hashMap.put(dinosaurId,CANT_ATTACK_TURNS);
        dinosaurIds.add(dinosaurId);
    }

    /**
     * Responsible to decrement the turn
     */
    public void decrementTurn(){
        int i=0;
        while(i<dinosaurIds.size()){
            int container= hashMap.get(dinosaurIds.get(i));
            container-=1;
            if(container==0){
                hashMap.remove(dinosaurIds.get(i));
                dinosaurIds.remove(dinosaurIds.get(i));
            }
            else{
                hashMap.replace(dinosaurIds.get(i),container);
            }
            i+=1;
        }
    }

    /**
     * Responsible to check if there is a prey
     * @param dinosaurID the unique dinosaur ID
     * @return true, when the prey is found
     * @return false, when the prey is not found
     */
    public  boolean gotPrey(int dinosaurID){
        if(hashMap.containsKey(dinosaurID))
            return  true;
        return false;
    }


}
