package types;

import java.util.ArrayList;
import java.util.List;

public class PokemonBase implements Comparable<PokemonBase>{
    
    private String dexNum;
    private String name;
    private Type type;
    private Type secondaryType;
    
    private int baseAtt;
    private int baseDef;
    private int baseHP;
    
    private List<String> fastMoves;
    private List<String> chargeMoves;
    private List<String> legacyMoves;
    
    private SecondMove secondCost;

    private List<String> evolutions;
    
    public PokemonBase() {
        this.fastMoves = new ArrayList<String>();
        this.chargeMoves = new ArrayList<String>();
        this.legacyMoves = new ArrayList<String>();
        this.evolutions = new ArrayList<String>();
    }

    public String getDexNum() {
        return dexNum;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Type getSecondaryType() {
        return secondaryType;
    }

    public int getBaseAtt() {
        return baseAtt;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public List<String> getFastMoves() {
        return fastMoves;
    }

    public List<String> getChargeMoves() {
        return chargeMoves;
    }

    public List<String> getLegacyMoves() {
        return legacyMoves;
    }

    public SecondMove getSecondCost() {
        return secondCost;
    }
    
    public List<String> getEvolutions() {
        return evolutions;
    }

    public void setDexNum(String dexNum) {
        this.dexNum = dexNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setSecondaryType(Type secondaryType) {
        this.secondaryType = secondaryType;
    }

    public void setBaseAtt(int baseAtt) {
        this.baseAtt = baseAtt;
    }

    public void setBaseDef(int baseDef) {
        this.baseDef = baseDef;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public void setFastMoves(List<String> fastMoves) {
        this.fastMoves = fastMoves;
    }

    public void setChargeMoves(List<String> chargeMoves) {
        this.chargeMoves = chargeMoves;
    }

    public void setLegacyMoves(List<String> legacyMoves) {
        this.legacyMoves = legacyMoves;
    }

    public void setSecondCost(SecondMove secondCost) {
        this.secondCost = secondCost;
    }
    
    public void setEvolutions(List<String> evolutions) {
        this.evolutions = evolutions;
    }

    @Override
    public int compareTo(PokemonBase poke) {
        return dexNum.compareTo(poke.dexNum);
    }
    
}
