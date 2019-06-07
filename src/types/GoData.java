package types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import types.outputs.IVSpreads;

public class GoData {

    private TreeSet<PokemonBase> pokeSet;
    private HashMap<String, PokemonBase> pokeMap;
    private HashMap<String, FastMove> fastMoveMap;
    private HashMap<String, ChargeMove> chargeMoveMap;

    public GoData() {
        this.pokeSet = new TreeSet<PokemonBase>();
        this.fastMoveMap = new HashMap<String, FastMove>();
        this.chargeMoveMap = new HashMap<String, ChargeMove>();
        this.pokeMap = new HashMap<String, PokemonBase>();
    }

    public GoData(TreeSet<PokemonBase> pokeSet, HashMap<String, FastMove> fastMoveMap,
            HashMap<String, ChargeMove> chargeMoveMap) {
        this.pokeSet = pokeSet;
        this.fastMoveMap = fastMoveMap;
        this.chargeMoveMap = chargeMoveMap;
        populatePokeMap();
    }

    public TreeSet<PokemonBase> getPokeSet() {
        return pokeSet;
    }

    public HashMap<String, FastMove> getFastMoveMap() {
        return fastMoveMap;
    }

    public HashMap<String, ChargeMove> getChargeMoveMap() {
        return chargeMoveMap;
    }

    public void setPokeSet(TreeSet<PokemonBase> pokeSet) {
        this.pokeSet = pokeSet;
        populatePokeMap();
    }

    public void setFastMoveMap(HashMap<String, FastMove> fastMoveMap) {
        this.fastMoveMap = fastMoveMap;
    }

    public void setChargeMoveMap(HashMap<String, ChargeMove> chargeMoveMap) {
        this.chargeMoveMap = chargeMoveMap;
    }
    
    public PokemonBase getPoke(String pokeName) {
        if (pokeMap == null || !pokeMap.containsKey(pokeName)) {
            return null;
        }
        return pokeMap.get(pokeName);
    }
    
    public List<PokemonInstance> getEvolutions(PokemonBase poke, IVSpreads ivs) {
        ArrayList<PokemonInstance> evos = new ArrayList<PokemonInstance>();
        
        if (poke.getEvolutions() == null) {
            return null;
        }
        for (String s: poke.getEvolutions()) {
            PokemonBase base = getPoke(s);
            if (base != null) {
                evos.add(new PokemonInstance(base, ivs));
            }
        }
        
        return evos;
    }
    
    public List<PokemonBase> getEvolutions(PokemonBase poke) {
        ArrayList<PokemonBase> evos = new ArrayList<PokemonBase>();
        
        if (poke.getEvolutions() == null) {
            return null;
        }
        for (String s: poke.getEvolutions()) {
            evos.add(getPoke(s));
        }
        
        return evos;
    }
    
    private void populatePokeMap() {
        pokeMap = new HashMap<String, PokemonBase>();
        
        for(PokemonBase poke: pokeSet) {
            pokeMap.put(poke.getName(), poke);
        }
    }
    
}
