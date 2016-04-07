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


    private int raceID, // race ID that player is currently in
            runnerSpeciesID, // ID of selected species
            health, // health of species
            status, // status of species (from items/boosts)
            itemID, // currently held item (0 if not holding anything)
            numItems, // number of items collected
            time, // current time of player
            score; // current score of player
    private float x, // x position of player
            y, // y position of player
            speed, // current speed of player
            power, // current power of player
            finalTime;
    private boolean right,
            left,
            jump,
            usedItem; // player used item?
    private PlayTimePlayer opponent;
    
    
    public PlayTimePlayer(int player_id, int raceID){
        super(player_id);
        this.raceID = raceID;
        
        // set defaults
        runnerSpeciesID = 31;
        health = 100;
        status = 0;
        itemID = 1;
        numItems = 0;
        time = 0;
        score = 0;
        x = 0;
        y = 0;
        speed = 1.0f;
        power = 1.0f;
        finalTime = 0.0f;
        right = false;
        left = false;
        jump = false;
        usedItem = false;
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
    
    public int getItemID() {
        return itemID;
    }
    
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    
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
     * @return if player jumped
     */
    public boolean isJump() {
        return jump;
    }

    /**
     * @param jump is player jumping?
     */
    public void setJump(boolean jump) {
        this.jump = jump;
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
    public int getNumItems() {
        return numItems;
    }

    /**
     * @param numItems number of collected items
     */
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }
    
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
    
    public float getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(float finalTime) {
        this.finalTime = finalTime;
    }
    


}
