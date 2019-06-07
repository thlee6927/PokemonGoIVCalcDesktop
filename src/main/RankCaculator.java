package main;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import types.GoData;
import types.PokemonBase;
import types.PokemonInstance;
import types.exceptions.LeagueEligibleException;
import utils.FileEncoder;
import utils.Utils;
import utils.calcs;

public class RankCaculator {

    public static void main(String[] args) {
        if (args.length == 0 || args[1].equals("help")) {
            System.out.println("Rank Cacluator Parameters:\n"
                    + "\trank [pokemonName] [form(opptional)] [attackIV] [defenseIV] [hpIV]");
            System.exit(0);
        }
        
        if (args.length != 5 && args.length != 4) {
            System.out.println("Rank Cacluator Parameters:\n"
                    + "\trank [pokemonName] [form(opptional)] [attackIV] [defenseIV] [hpIV]");
            System.exit(0);
        }

        String name = args[0].toLowerCase();
        int att;
        int def;
        int hp;
        
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        
        if (args.length == 4) {
            att = Integer.parseInt(args[1]);
            def = Integer.parseInt(args[2]);
            hp = Integer.parseInt(args[3]);
        } else {
            name = name + " " + parseForm(args[1].toLowerCase());
            att = Integer.parseInt(args[2]);
            def = Integer.parseInt(args[3]);
            hp = Integer.parseInt(args[4]);
        }
        
        ObjectMapper mapper = new ObjectMapper();
        GoData goData = FileEncoder.decryptFile("PokeDataEncrypt", "mudkips", mapper);
        
        PokemonBase base = goData.getPoke(name);
        
        if (base == null) {
            System.out.println("Unknown pokemon: " + name);
            System.exit(0);
        }
        
        HashMap<PokemonInstance, Integer> rankMap = calcs.calcRanks(base);
        
        PokemonInstance poke = new PokemonInstance(base);
        poke.setLevel(1);
        poke.setAttIV(att);
        poke.setDefIV(def);
        poke.setHpIV(hp);
        
        try {
            poke = calcs.calcPvpLevel(poke, Utils.GREAT_LEAGUE);
        } catch (LeagueEligibleException e) {
        }
        
        System.out.println(name);
        System.out.println("Rank: " + rankMap.get(poke));
        System.out.println("Level: " + poke.printLevel());
        System.out.println("CP: " + calcs.calcCP(poke));
        System.out.println(calcs.printPokeStats(poke));
        
    }

    private static String parseForm(String form) {
        if (form.equals("alolan")) {
            return "(Alola Form)";
        }
        if (form.equals("rain")) {
            return "(Rainy Form)";
        }
        if (form.equals("snow")) {
            return "(Snowy Form)";
        }
        if (form.equals("sun")) {
            return "(Sunny Form)";
        }
        if (form.equals("attack")) {
            return "(Attack Forme)";
        }
        if (form.equals("defense")) {
            return "(Defense Forme)";
        }
        if (form.equals("speed")) {
            return "(Speed Forme)";
        }
        if (form.equals("sandy")) {
            return "(Sandy Cloak)";
        }
        if (form.equals("trash")) {
            return "(Trash cloak)";
        }
        if (form.equals("fan")) {
            return "(Fan Rotom)";
        }
        if (form.equals("frost")) {
            return "(Frost Rotom)";
        }
        if (form.equals("heat")) {
            return "(Heat Rotom)";
        }
        if (form.equals("mow")) {
            return "(Mow Rotom)";
        }
        if (form.equals("wash")) {
            return "(Wash Rotom)";
        }
        if (form.equals("origin")) {
            return "(Origin Forme)";
        }
        if (form.equals("sky")) {
            return "(Sky Forme)";
        }
        return null;
    }
    
}
