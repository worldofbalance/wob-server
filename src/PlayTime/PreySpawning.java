/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayTime;
import java.util.HashMap;
import java.util.Map;
import metadata.Constants;
import model.Prey;

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
      if(i<8){
          prey.setSpecies_id(1);
      }
      else if((i>=8)&&(i<15)){
           prey.setSpecies_id(2);
        }
      else
      {
          prey.setSpecies_id(3);
      }
      prey.setRotation(0.0f);
      map.put(i,prey);
 }
 }

}
