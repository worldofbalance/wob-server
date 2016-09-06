/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.PlayTime;
import java.util.HashMap;
import java.util.Map;
import shared.metadata.Constants;
import sdv.model.Prey;

/**
 *
 * @author anu
 */
public class PreySpawning {
 
 private static PreySpawning spawn = new PreySpawning();
 public Map<Integer,Prey> map = new HashMap<Integer, Prey>();
 public static PreySpawning getInstance(){
 return spawn;
 }
 public void spawn(){
 for(int i =1;i<= Constants.NUM_PREY;i++){
        
      Prey prey = new Prey();
      float x = Constants.X_MIN + (float)(Math.random() * ((Constants.X_MAX - Constants.X_MIN) + 1));
      float y = Constants.Y_MIN + (float)(Math.random() * ((Constants.Y_MAX - Constants.Y_MIN) + 1));
      prey.setX(x);
      prey.setY(y);
      prey.setPrey_id(i);
      if(i<7){
          prey.setSpecies_id(0);
      }
      else if((i>=7)&&(i<12)){
           prey.setSpecies_id(1);
        }
      else if((i>=12)&&(i<16))
      {
          prey.setSpecies_id(2);
      }
       else if((i>=16)&&(i<20))
      {
          prey.setSpecies_id(3);
      }
       else if((i>=20)&&(i<26))
      {
          prey.setSpecies_id(4);
      }
       else if((i>=26)&&(i<32))
      {
          prey.setSpecies_id(5);
      }
        else if((i>=32)&&(i<36))
      {
          prey.setSpecies_id(6);
      }
        else if((i>=36)&&(i<=40))
      {
          prey.setSpecies_id(7);
      }
      prey.setRotation(0.0f);
      map.put(i,prey);
 }
 }

}
