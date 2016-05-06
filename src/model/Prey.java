/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author anu
 */
public class Prey {
    
    int prey_id;
    int species_id;
    float x,y, rotation;
    boolean isAlive;

    public Prey(){}
    
    public Prey(int prey_id, float x, float y){
        this.prey_id=prey_id;
        this.x = x;
        this.y =y;
        isAlive =true;
    }
    
    public int getPrey_id() {
        return prey_id;
    }

    public void setPrey_id(int prey_id) {
        this.prey_id = prey_id;
    }

    public int getSpecies_id() {
        return this.species_id;
    }
        
    public void setSpecies_id(int species_id) {
        this.species_id = species_id;
    }
        
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public float getRotation(){
        return this.rotation;
    }
    
    public void setRotation(float rot){
        this.rotation = rot;
    }
    
}
