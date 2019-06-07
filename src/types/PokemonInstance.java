package types;

import types.outputs.IVSpreads;

public class PokemonInstance {

    private String nickname;
    private PokemonBase base;
    
    private int level;
    private boolean halfLevel;
    
    private int attIV;
    private int defIV;
    private int hpIV;
    
    public PokemonInstance(PokemonBase base) {
        this.base = base;
        this.nickname = base.getName();
    }
    
    public PokemonInstance(PokemonBase base, IVSpreads ivs) {
        this(base);
        
        this.level = ivs.getLevel();
        this.halfLevel = ivs.isHalf();
        this.attIV = ivs.getAtt();
        this.defIV = ivs.getDef();
        this.hpIV = ivs.getHp();
    }
    
    public PokemonInstance(PokemonInstance poke) {
        this(poke.base);
        
        this.level = poke.level;
        this.halfLevel = poke.halfLevel;
        
        this.attIV = poke.attIV;
        this.defIV = poke.defIV;
        this.hpIV = poke.hpIV;
    }
    
    public void levelUp() {
        if (level < 40) {
            if (halfLevel) {
                level++;
                halfLevel = false;
            } else {
                halfLevel = true;
            }
        }
    }
    
    public void levelDown() {
        if (level > 1 || halfLevel) {
            if (!halfLevel) {
                level--;
                halfLevel = true;
            } else {
                halfLevel = false;
            }
        }
    }
    
    public void maxLevel() {
        halfLevel = false;
        level = 40;
    }
    
    public String printLevel() {
        String s = level +"";
        if (halfLevel) {
            s+=".5";
        }
        
        return s;
    }
    
    public String printIVs() {
        return attIV + "/" + defIV + "/" + hpIV;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + attIV;
        result = prime * result + defIV;
        result = prime * result + hpIV;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PokemonInstance other = (PokemonInstance) obj;
        if (attIV != other.attIV)
            return false;
        if (defIV != other.defIV)
            return false;
        if (hpIV != other.hpIV)
            return false;
        return true;
    }

    public String getNickname() {
        return nickname;
    }
    public PokemonBase getBase() {
        return base;
    }
    public int getLevel() {
        return level;
    }
    public boolean isHalfLevel() {
        return halfLevel;
    }
    public int getAttIV() {
        return attIV;
    }
    public int getDefIV() {
        return defIV;
    }
    public int getHpIV() {
        return hpIV;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setBase(PokemonBase base) {
        this.base = base;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setHalfLevel(boolean halfLevel) {
        this.halfLevel = halfLevel;
    }
    public void setAttIV(int attIV) {
        this.attIV = attIV;
    }
    public void setDefIV(int defIV) {
        this.defIV = defIV;
    }
    public void setHpIV(int hpIV) {
        this.hpIV = hpIV;
    }
    
}
