package types.outputs;

public class IVSpreads implements Comparable<IVSpreads>{
    private int level;
    private boolean half;
    private int att;
    private int def;
    private int hp;
    
    public IVSpreads(int level, boolean half, int att, int def, int hp) {
        this.level = level;
        this.half = half;
        this.att = att;
        this.def = def;
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }
    
    public boolean isHalf() {
        return half;
    }

    public int getAtt() {
        return att;
    }

    public int getDef() {
        return def;
    }

    public int getHp() {
        return hp;
    }
    
    public String getLevelString() {
        if (half) { 
            return level + ".5";
        }
        return level+"";
    }
    
    public String getIVSpread() {
        return att + "\\" + def + "\\" + hp;
    }
    
    public int getPercent() {
        return (100 * (att + def + hp)) / 45;
    }

    @Override
    public int compareTo(IVSpreads iv) {
        if (level != iv.level) {
            return level - iv.level;
        }
        if (half != iv.half) {
            if (half)
                return 1;
            else
                return -1;
        }
        if (att != iv.att)
            return att - iv.att;
        if (def != iv.def)
            return def - iv.def;
        return hp - iv.hp;
    }
    
    
}
