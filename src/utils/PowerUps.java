package utils;

import java.util.HashMap;

/**
 * Constant Mapping to help determine the level of a pokemon, used in calculations
 * 
 * @author Thomas Lee
 *
 */

public class PowerUps {
    static int powerUpCosts[] = {200, 200, 400, 400, 600, 
            600, 800, 800, 1000, 1000, 
            1300, 1300, 1600, 1600, 1900, 
            1900, 2200, 2200, 2500, 2500, 
            3000, 3000, 3500, 3500, 4000, 
            4000, 4500, 4500, 5000, 5000, 
            6000, 6000, 7000, 7000, 8000, 
            8000, 9000, 9000, 10000};
    
    static HashMap<Integer, Integer> powerUpLevels;
    
    static {
        powerUpLevels = new HashMap<Integer, Integer>();
        for(int i = 0; i< powerUpCosts.length; i++) {
            if(!powerUpLevels.containsKey(powerUpCosts[i])) {
                powerUpLevels.put(powerUpCosts[i], i+1);
            }
        }
        
        powerUpLevels.put(0, 40);
    }
    
}
