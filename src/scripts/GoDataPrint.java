package scripts;

import com.fasterxml.jackson.databind.ObjectMapper;

import types.GoData;
import utils.FileEncoder;
import utils.Utils;

/**
 * This script is just to output specified data from the datafiles, just for manual examination
 * 
 * @author Thomas Lee
 *
 */

public class GoDataPrint {

    public static void main(String[] args) {
        
        ObjectMapper mapper = new ObjectMapper();

        GoData data = FileEncoder.decryptFile("PokeDataEncrypt", "mudkips", mapper);
        
//        utils.printPokeSet(data.getPokeSet());
        Utils.printFastMoves(data.getFastMoveMap());
//        utils.printChargeMoves(data.getChargeMoveMap());
        
    }
    
}
