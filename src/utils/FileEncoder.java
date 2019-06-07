package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import types.GoData;

/**
 * 
 * @author Thomas Lee
 *
 */

public class FileEncoder {
    
    @SuppressWarnings("resource")
    public static GoData decryptFile(String filePath, String key, ObjectMapper mapper) {
        
        GoData data = null;
        
        try {
            Scanner scan = new Scanner(new File(filePath));
            String decrypted = AESTool.decrypt(scan.nextLine(), key);
            
            if (decrypted == null) {
                return null;
            }
            data = mapper.readValue(decrypted, GoData.class);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void encryptAndSaveFile(String filePath, GoData goData, String password, ObjectMapper mapper) {
        try {
            File f = new File(filePath);
            f.createNewFile();
            
            String data = mapper.writeValueAsString(goData);
            
            String encrypted = AESTool.encrypt(data, password);
            
            FileWriter writer = new FileWriter(f);

            writer.write(encrypted);
//            writer.write(data);
            writer.close();
        } catch (IOException e) {
        }
    }
}
