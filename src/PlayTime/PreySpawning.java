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
      prey.setIsAlive(true);
      prey.setPrey_id(i);
      map.put(i,prey);
 }
 }

}
