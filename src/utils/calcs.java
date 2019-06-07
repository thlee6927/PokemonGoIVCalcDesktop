package utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import types.PokemonBase;
import types.PokemonInstance;
import types.exceptions.LeagueEligibleException;
import types.inputs.PokemonIVInput;
import types.outputs.IVSpreads;

/**
 * Just a class file holding all the functions to calculate stats
 * @author Thomas Lee
 *
 */

public class calcs {
    
    /**
     * Creates a HashMap of the ranks for each possible IV combination for the input pokemon
     * 
     * Ranks are sorted based on the stat product pokemon when at a pvp level
     * 
     * @param base
     * @return
     */
    
    public static HashMap<PokemonInstance, Integer> calcRanks(PokemonBase base) {
        TreeSet<PokemonInstance> ranks = calcSortedRanks(base);
        
        HashMap<PokemonInstance, Integer> rankMap = new HashMap<PokemonInstance, Integer>();
        
        int i = 1;
        for (PokemonInstance pr: ranks) {
            rankMap.put(pr, i);
            i++;
        }
        
        return rankMap;
    }

    /**
     * Creates the sorted Set of the ranks for each possible IV combination for the input pokemon
     * 
     * Ranks are sorted based on the stat product pokemon when at a pvp level
     * 
     * @param base
     * @return
     */
    
    public static TreeSet<PokemonInstance> calcSortedRanks(PokemonBase base) {
        PokemonInstance poke = new PokemonInstance(base);
        
        TreeSet<PokemonInstance> ranks = new TreeSet<>(new Comparator<PokemonInstance>() {

            @Override
            public int compare(PokemonInstance p1, PokemonInstance p2) {
                int p1Product = calcStatProduct(p1);
                int p2Product = calcStatProduct(p2);
                
                if (p1Product != p2Product) {
                    return p2Product - p1Product;
                } else {
                    int p1CP = calcCP(p1);
                    int p2CP = calcCP(p2);
                    if (p1CP != p2CP) {
                        return p2CP - p1CP;
                    }
                    if (p1.getDefIV() != p2.getDefIV()) {
                        return p1.getDefIV() - p2.getDefIV();
                    } else if (p1.getHpIV() != p2.getHpIV()) {
                        return p1.getHpIV() - p2.getHpIV();
                    } else {
                        return p1.getAttIV() - p2.getAttIV();
                    }
                }
            }
        });
        
        poke.setLevel(1);
        
        for (int att = 0; att <= 15; att++) {
            for (int def = 0; def <=15; def++) {
                for (int hp = 0; hp <= 15; hp++) {
                    poke.setAttIV(att);
                    poke.setDefIV(def);
                    poke.setHpIV(hp);
                    
                    try {
                        ranks.add(calcPvpLevel(poke, Utils.GREAT_LEAGUE));
                    } catch (LeagueEligibleException e) {
                    }
                }
            }
        }
        
        return ranks;
    }
    
    /**
     * Calculates the level at which the input pokemon may be elible for pvp
     * 
     * Creates a new instance of the pokemon and incrementally levels up the pokemon until it hits the cap
     * 
     * @param poke
     * @param league integer stating the cap for pvp
     * @return PokemonInstance a new instance of the input pokemon at its max pvp eligible level
     * @throws LeagueEligibleException Thrown when input pokemon's level is already too high to be eligible for pvp
     */
    
    public static PokemonInstance calcPvpLevel(PokemonInstance poke, int league) throws LeagueEligibleException {
        int curCp = calcCP(poke);
        if (curCp > league) {
            throw new LeagueEligibleException();
        }
        
        PokemonInstance check = new PokemonInstance(poke);
        
        do {
            check.levelUp();
            curCp = calcCP(check);
        }while (curCp < league && check.getLevel() != 40);
        
        if (curCp > league) {
            check.levelDown();
        }
        
        return check;
    }
    
    /**
     * Essentially returns the CP of the pokemon at the maximum level
     * 
     * @param poke
     * @return CP of input at level 40
     */
    
    public static int calcMaxCP(PokemonInstance poke) {
        PokemonInstance max = new PokemonInstance(poke);
        
        max.maxLevel();
        
        return calcCP(max);
    }
    
    /**
     * Calculates the possible ivs for a pokemon given the information from the status screen
     * 
     * @param inputs
     * @return
     */
    
    public static TreeSet<IVSpreads> calcIVs(PokemonIVInput inputs) {
        TreeSet<IVSpreads> possible = new TreeSet<IVSpreads>();

        if (PowerUps.powerUpLevels.containsKey(inputs.getLvl())) {
            int level = PowerUps.powerUpLevels.get(inputs.getLvl());
            
            PokemonInstance inst = new PokemonInstance(inputs.getBase());
            
            for(int att = 0; att < 16; att++) {
                for(int def = 0; def < 16; def++) {
                    for(int sta = 0; sta < 16; sta++) {
                        if (checkIVs(inputs, att, def, sta)) {
                            inst.setAttIV(att);
                            inst.setDefIV(def);
                            inst.setHpIV(sta);

                            inst.setLevel(level);
                            inst.setHalfLevel(false);
                            
                            if (calcCP(inst) == inputs.getCp() && calcHP(inst) == inputs.getHp()) {
                                IVSpreads blah = new IVSpreads(level, false, att, def, sta);
                                possible.add(blah);
                            }

                            inst.setLevel(level+1);
                            if (calcCP(inst) == inputs.getCp() && calcHP(inst) == inputs.getHp()) {
                                IVSpreads blah = new IVSpreads(level+1, false, att, def, sta);
                                possible.add(blah);
                            }
                            
                            if (!inputs.isVanil()) {
                                inst.setHalfLevel(true);
                                if (calcCP(inst) == inputs.getCp() && calcHP(inst) == inputs.getHp()) {
                                    IVSpreads blah = new IVSpreads(level+1, true, att, def, sta);
                                    possible.add(blah);
                                }
                                inst.setLevel(level);
                                if (calcCP(inst) == inputs.getCp() && calcHP(inst) == inputs.getHp()) {
                                    IVSpreads blah = new IVSpreads(level, true, att, def, sta);
                                    possible.add(blah);
                                }
                                
                            }
                        }
                        
                        
                    }
                }
            }
            
        }
        
        return possible;
    }

    /**
     * Checks whether inputed iv spread is valid with the given requirements from the input
     * 
     * @param inputs
     * @param att
     * @param def
     * @param hp
     * @return
     */
    
    private static boolean checkIVs(PokemonIVInput inputs, int att, int def, int hp) {
        int percent = (100 * (att + def + hp) ) / 45;

        if (percent >= inputs.getRating().ivFloor && percent <= inputs.getRating().ivCeil) {
            int max = Math.max(att, Math.max(def, hp));
            
            if (att < inputs.getFloor().getIvFloor() || def < inputs.getFloor().getIvFloor() || hp < inputs.getFloor().getIvFloor()) {
                return false;
            }
            
            if (inputs.getBest() == null) {
                return true;
            } else if (max < inputs.getBest().ivFloor || max > inputs.getBest().ivCeil){
                return false;
            }
            
            if (att == max && !inputs.isAttB()) {
                return false;
            } 
            if (def == max && !inputs.isDefB()) {
                return false;
            } 
            if (hp == max && !inputs.isHpB()) {
                return false;
            }
            return true;
        }
        
        return false;
    }

    /**
     * Calculates and returns the CP of the inputted pokemon
     * 
     * @param poke
     * @return
     */
    
    public static int calcCP(PokemonInstance poke) {
        float cp = (float) (getBaseAttack(poke) * Math.sqrt(getBaseDefense(poke)) * Math.sqrt(getBaseHP(poke)) * Math.pow(cpm.getCPMultiplier(poke.getLevel(), poke.isHalfLevel()), 2));
        cp = cp/10.0f;
        
        return (int) Math.floor(cp);
    }
    
    public static int calcStatProduct(PokemonInstance poke) {
        return (int) Math.floor(calcRawAttack(poke) * calcRawDef(poke) * calcHP(poke));
    }

    public static int calcAttack(PokemonInstance poke) {
        float attack = getBaseAttack(poke);
        
        return (int) Math.floor(attack * cpm.getCPMultiplier(poke.getLevel(), poke.isHalfLevel()));
    }

    public static float calcRawAttack(PokemonInstance poke) {
        float attack = getBaseAttack(poke);
        
        return attack * cpm.getCPMultiplier(poke.getLevel(), poke.isHalfLevel());
    }
    
    public static int calcDef(PokemonInstance poke) {
        float def = getBaseDefense(poke);
        
        return (int) Math.floor(def * cpm.getCPMultiplier(poke.getLevel(), poke.isHalfLevel()));
    }
    
    public static float calcRawDef(PokemonInstance poke) {
        float def = getBaseDefense(poke);
        
        return def * cpm.getCPMultiplier(poke.getLevel(), poke.isHalfLevel());
    }
    
    public static int calcHP(PokemonInstance poke) {
        float hp = getBaseHP(poke);
        
        return (int) Math.floor(hp * cpm.getCPMultiplier(poke.getLevel(), poke.isHalfLevel()));
    }
    
    private static int getBaseAttack(PokemonInstance poke) {
        return poke.getBase().getBaseAtt() + poke.getAttIV();
    }
    
    private static int getBaseDefense(PokemonInstance poke) {
        return poke.getBase().getBaseDef() + poke.getDefIV();
    }
    
    private static int getBaseHP(PokemonInstance poke) {
        return poke.getBase().getBaseHP() + poke.getHpIV();
    }
    
    public static String printPokeStats(PokemonInstance poke) {
        return "Attack: " + calcAttack(poke) + "\tDefense: " + calcDef(poke) + "\tHP: " + calcHP(poke);
    }
    
}
