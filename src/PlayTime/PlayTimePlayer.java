/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayTime;

import core.GameClient;
import model.Player;

/**
 *
 * @author anu
 */
public class PlayTimePlayer extends Player {


    private int raceID, // play ID that player is currently in
            runnerSpeciesID, // ID of selected species
            maxHealth, //maximum health of player (can be changed by upgrades)
            health, // health of species
            status, // status of species (from items/boosts)
            //itemID, // currently held item (0 if not holding anything)
            //numItems, // number of items collected
            time, // current time of player
            score, // current score of player
            stomach, //current stomach of player
            maxStamina, //maximum stamina
            stamina, // stamina (used for character sprinting)
            scoreflag; // to keep track whether score updated or not
    private float x, // x position of player
            y, // y position of player
            speed, // current speed of player
            power, // current power of player
            finalScore;
    private boolean right,
            left,
            jump,
            usedItem; // player used item?
    private PlayTimePlayer opponent;
    
    public PlayTimePlayer(int player_id){super(player_id);}
    public PlayTimePlayer(int player_id, int raceID){
        super(player_id);
        this.raceID = raceID;
        
        // set defaults
        runnerSpeciesID = 31;
        health = 100;
        status = 0;
        //itemID = 1;
        //numItems = 0;
        time = 0;
        score = 0;
        x = 0;
        y = 0;
        speed = 1.0f;
        power = 1.0f;
        finalScore = 0.0f;
        scoreflag =0;
        right = false;
        left = false;
        jump = false;
        usedItem = false;
    }

    public int getScoreflag() {
        return scoreflag;
    }

    public void setScoreflag(int scoreflag) {
        this.scoreflag = scoreflag;
    }
    
    public int getRaceID() {
        return raceID;
    }

    public void setRaceID(int raceID){
        this.raceID = raceID;
    }
    
    public int getRunnerSpeciesID() {
        return runnerSpeciesID;
    }
    
    public void setRunnerSpeciesID(int runnerSpeciesID) {
        this.runnerSpeciesID = runnerSpeciesID;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public int getMaxStamina() {
        return this.maxStamina;
    }
    
    public void setMaxStamina(int maxStamina){
        this.maxStamina = maxStamina;
    }
    
    public int getStamina() {
        return this.stamina;
    }
    
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
//    public int getItemID() {
//        return itemID;
//    }
//    
//    public void setItemID(int itemID) {
//        this.itemID = itemID;
//    }
    
    public int getTime() {
        return time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
//    public RacePlayer getOpponent() {
//        return opponent;
//    }
//
//    public RacePlayer setOpponent(RacePlayer opponent) {
//        return this.opponent = opponent;
//    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public float setX(float x) {
        return this.x = x;
    }

    public float setY(float y) {
        return this.y = y;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public int getStomach() {
        return this.stomach;
    }
    
    public void setStomach(int stomach) {
        this.stomach = stomach;
    }
    
    public float getPower() {
        return power;
    }
    
    public void setPower(float power) {
        this.power = power;
    }

    /**
     * @return if player is going right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @param right is player going right?
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * @return if player is going left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @param left is player going left?
     */
    public void setLeft(boolean left) {
        this.left = left;
    }


    /**
     * @return usedItem
     */
    public boolean usedItem() {
        return usedItem;
    }

    /**
     * @param usedItem used item
     */
    public void setUsedItem(boolean usedItem) {
        this.usedItem = usedItem;
    }
    
    /**
     * @return number of collected items
     */
    //public int getNumItems() {
//        return numItems;
//    }
//
//    /**
//     * @param numItems number of collected items
//     */
//    public void setNumItems(int numItems) {
//        this.numItems = numItems;
//    }
    
    /**
     * @return the opponent
     */
    public PlayTimePlayer getOpponent() {
        return opponent;
    }

    /**
     * @param opponent the opponent to set
     */
    public void setOpponent(PlayTimePlayer opponent) {
        this.opponent = opponent;
    }
    
    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }
    


}
