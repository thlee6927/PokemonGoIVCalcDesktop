package utils;

import java.util.HashMap;
import java.util.TreeSet;

import types.ChargeMove;
import types.FastMove;
import types.PokemonBase;

/**
 * Simple collection of helper methods for utility purposes
 * 
 * @author Thomas Lee
 *
 */

public class Utils {

    public static int GREAT_LEAGUE = 1500;
    public static int ULTRA_LEAGUE = 2500;
    
    public static void printPokeSet(TreeSet<PokemonBase> pokeSet) {
        for (PokemonBase poke: pokeSet) {
            System.out.println(poke.getDexNum() + ": " + poke.getName());
            System.out.print(poke.getType());
            if (poke.getSecondaryType() != null) {
                System.out.println("/" + poke.getSecondaryType());
            } else {
                System.out.println();
            }
            System.out.println(poke.getBaseHP() + "/" + poke.getBaseAtt() + "/" + poke.getBaseDef());
            System.out.println(poke.getFastMoves());
            System.out.println(poke.getChargeMoves());
            System.out.println();
        }
    }

    public static void printFastMoves(HashMap<String, FastMove> moveMap) {
        for (String s: moveMap.keySet()) {
            FastMove move = moveMap.get(s);

            System.out.println(move.getName());
            System.out.println(move.getType().name() + " Type");
            System.out.println("Power: " + move.getPvpPower());
            System.out.println("Energy: " + move.getPvpEnergy());
            System.out.println("Duration: " + move.getPvpDuration() + " Seconds\n");
        }
        
        System.out.println(moveMap.size() + " moves");
    }

    public static void printChargeMoves(HashMap<String, ChargeMove> moveMap) {
        for (String s: moveMap.keySet()) {
            
            ChargeMove move = moveMap.get(s);
            
            System.out.println(move.getName());
            System.out.println(move.getType().name() + " Type");
            System.out.println("Power: " + move.getPvpPower());
            System.out.println("Energy: " + move.getPvpEnergyReq());
            if (move.getPvpEffect() != null) {
                System.out.println(move.getEffectPercent() + "% chance of " + move.getPvpEffect());
            }
            System.out.println();
        }
    }
}
