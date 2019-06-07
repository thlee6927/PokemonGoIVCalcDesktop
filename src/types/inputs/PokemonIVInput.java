package types.inputs;

import types.PokemonBase;

public class PokemonIVInput {
    private PokemonBase base;
    private int cp;
    private int hp;
    private int lvl;
    private boolean vanil;
    private EncounterType floor;
    private IVRating rating;
    private BestRating best;
    private boolean attB;
    private boolean defB;
    private boolean hpB;

    public PokemonIVInput(PokemonBase base, int cp, int hp, int lvl, boolean vanil, EncounterType floor) {
        this(base, cp, hp, lvl, vanil, floor, IVRating.NONE);
    }
    
    public PokemonIVInput(PokemonBase base, int cp, int hp, int lvl, boolean vanil, EncounterType floor, IVRating rating) {
        this(base, cp, hp, lvl, vanil, floor, rating, false, false, false, null);
    }

    public PokemonIVInput(PokemonBase base, int cp, int hp, int lvl, boolean vanil, EncounterType floor, IVRating rating, boolean attB, boolean defB,
            boolean hpB, BestRating best) {
        this.base = base;
        this.cp = cp;
        this.hp = hp;
        this.lvl = lvl;
        this.vanil = vanil;
        this.floor = floor;
        this.rating = rating;
        this.attB = attB;
        this.defB = defB;
        this.hpB = hpB;
        this.best = best;
    }

    public PokemonBase getBase() {
        return base;
    }

    public int getCp() {
        return cp;
    }

    public int getHp() {
        return hp;
    }

    public int getLvl() {
        return lvl;
    }

    public boolean isVanil() {
        return vanil;
    }

    public EncounterType getFloor() {
        return floor;
    }

    public IVRating getRating() {
        return rating;
    }

    public BestRating getBest() {
        return best;
    }

    public boolean isAttB() {
        return attB;
    }

    public boolean isDefB() {
        return defB;
    }

    public boolean isHpB() {
        return hpB;
    }

    public void setBase(PokemonBase base) {
        this.base = base;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setVanil(boolean vanil) {
        this.vanil = vanil;
    }

    public void setFloor(EncounterType floor) {
        this.floor = floor;
    }

    public void setRating(IVRating rating) {
        this.rating = rating;
    }

    public void setBest(BestRating best) {
        this.best = best;
    }

    public void setAttB(boolean attB) {
        this.attB = attB;
    }

    public void setDefB(boolean defB) {
        this.defB = defB;
    }

    public void setHpB(boolean hpB) {
        this.hpB = hpB;
    }
    
}
