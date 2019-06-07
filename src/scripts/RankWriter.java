package scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

import types.GoData;
import types.PokemonBase;
import types.PokemonInstance;
import utils.FileEncoder;
import utils.calcs;

/**
 * 
 * This script goes through every pokemon (including forms) and creates a binary file for the ranks for the pokemon
 * 
 * @author Thomas Lee
 *
 */

public class RankWriter {
    
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        GoData goData = FileEncoder.decryptFile("PokeDataEncrypt", "######", mapper);
        
        for (PokemonBase poke: goData.getPokeSet()) {
            writePokeRankFile(poke);
        }
        
        
    }
    
    /***
     * Writes a binary file of the rank list for the inputed pokemon
     * 
     * The format of the binary file is thus: the 6 bytes of the file is the base stats (attack, defense, hp in that order), with 2 bytes for each stat
     * 
     * then a 4096 long list of every iv combination (sorted by its rank): 
     *      - 1 byte int for attack iv (0-15)
     *      - 1 byte int for defense iv (0-15)
     *      - 1 byte int for hp iv (0-15)
     *      - 1 byte int for level for pvp eligibility (1-40)
     *      - 1 byte boolean to indicate half level
     *      - 2 bytes int for the CP at the given level (0-1500)
     *      - 2 bytes int for attack stat at given level
     *      - 2 bytes int for defense stat at given level
     *      - 2 bytes int for hp stat at given level
     * 
     * @param pokeBase
     */
    
    public static void writePokeRankFile(PokemonBase pokeBase) {
        try {
            FileOutputStream out = new FileOutputStream(new File("Pokes/" + pokeBase.getName()));
            
            TreeSet<PokemonInstance> rankMap = calcs.calcSortedRanks(pokeBase);
            
            out.write(reverseBytes(ByteBuffer.allocate(4).putInt(pokeBase.getBaseAtt()).array()));
            out.write(reverseBytes(ByteBuffer.allocate(4).putInt(pokeBase.getBaseDef()).array()));
            out.write(reverseBytes(ByteBuffer.allocate(4).putInt(pokeBase.getBaseHP()).array()));
            
            for (PokemonInstance poke: rankMap) {
                out.write(poke.getAttIV());
                out.write(poke.getDefIV());
                out.write(poke.getHpIV());
                out.write(poke.getLevel());
                if (poke.isHalfLevel()) {
                    out.write(1);
                } else {
                    out.write(0);
                }
                out.write(reverseBytes(ByteBuffer.allocate(4).putInt(calcs.calcCP(poke)).array()));
                out.write(reverseBytes(ByteBuffer.allocate(4).putInt(calcs.calcAttack(poke)).array()));
                out.write(reverseBytes(ByteBuffer.allocate(4).putInt(calcs.calcDef(poke)).array()));
                out.write(reverseBytes(ByteBuffer.allocate(4).putInt(calcs.calcHP(poke)).array()));
            }
            
            out.close();
            
            System.out.println("Wrote " + pokeBase.getName() + " File!");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Machine script is run on is big endian. This method makes it little endian
     * 
     * @param blah
     * @return byte array
     */
    
    public static byte[] reverseBytes(byte blah[]) {
        byte ret[] = {blah[3], blah[2]};
        
        return ret;
    }
    
}
