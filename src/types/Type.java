package types;

public enum Type {
    
    BUG, DARK, DRAGON, ELECTRIC, FAIRY, FIGHTING, FIRE, FLYING, GHOST,
    GRASS, GROUND, ICE, NORMAL, POISON, PSYCHIC, ROCK, STEEL, WATER;
    
    private float[] defense = {1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f};
    
    static {
        //BUG
        BUG.defense[FIGHTING.ordinal()] = 0.5f;
        BUG.defense[GRASS.ordinal()] = 0.5f;
        BUG.defense[GROUND.ordinal()] = 0.5f;
        
        BUG.defense[FIRE.ordinal()] = 2f;
        BUG.defense[FLYING.ordinal()] = 2f;
        BUG.defense[ROCK.ordinal()] = 2f;
        
        //DARK
        DARK.defense[PSYCHIC.ordinal()] = 0.25f;
        DARK.defense[DARK.ordinal()] = 0.5f;
        DARK.defense[GHOST.ordinal()] = 0.5f;
        
        DARK.defense[BUG.ordinal()] = 2f;
        DARK.defense[FAIRY.ordinal()] = 2f;
        DARK.defense[FIGHTING.ordinal()] = 2f;
        
        //DRAGON
        DRAGON.defense[ELECTRIC.ordinal()] = 0.5f;
        DRAGON.defense[FIRE.ordinal()] = 0.5f;
        DRAGON.defense[GRASS.ordinal()] = 0.5f;
        DRAGON.defense[WATER.ordinal()] = 0.5f;
        
        DRAGON.defense[DRAGON.ordinal()] = 2f;
        DRAGON.defense[FAIRY.ordinal()] = 2f;
        DRAGON.defense[ICE.ordinal()] = 2f;
        
        //ELECTRIC
        ELECTRIC.defense[ELECTRIC.ordinal()] = 0.5f;
        ELECTRIC.defense[FLYING.ordinal()] = 0.5f;
        ELECTRIC.defense[STEEL.ordinal()] = 0.5f;
        
        ELECTRIC.defense[GROUND.ordinal()] = 2f;
        
        //FAIRY
        FAIRY.defense[DRAGON.ordinal()] = 0.25f;
        FAIRY.defense[BUG.ordinal()] = 0.5f;
        FAIRY.defense[DARK.ordinal()] = 0.5f;
        FAIRY.defense[FIGHTING.ordinal()] = 0.5f;
        
        FAIRY.defense[POISON.ordinal()] = 2f;
        FAIRY.defense[STEEL.ordinal()] = 2f;
        
        //FIGHTING
        FIGHTING.defense[BUG.ordinal()] = 0.5f;
        FIGHTING.defense[DARK.ordinal()] = 0.5f;
        FIGHTING.defense[ROCK.ordinal()] = 0.5f;
        
        FIGHTING.defense[FAIRY.ordinal()] = 2f;
        FIGHTING.defense[FLYING.ordinal()] = 2f;
        FIGHTING.defense[PSYCHIC.ordinal()] = 2f;
        
        //FIRE
        FIRE.defense[BUG.ordinal()] = 0.5f;
        FIRE.defense[FAIRY.ordinal()] = 0.5f;
        FIRE.defense[FIRE.ordinal()] = 0.5f;
        FIRE.defense[GRASS.ordinal()] = 0.5f;
        FIRE.defense[ICE.ordinal()] = 0.5f;
        FIRE.defense[STEEL.ordinal()] = 0.5f;
        
        FIRE.defense[GROUND.ordinal()] = 2f;
        FIRE.defense[ROCK.ordinal()] = 2f;
        FIRE.defense[WATER.ordinal()] = 2f;
        
        //FLYING
        FLYING.defense[GROUND.ordinal()] = 0.25f;
        FLYING.defense[BUG.ordinal()] = 0.5f;
        FLYING.defense[FIGHTING.ordinal()] = 0.5f;
        FLYING.defense[GRASS.ordinal()] = 0.5f;
        
        FLYING.defense[ELECTRIC.ordinal()] = 2f;
        FLYING.defense[ICE.ordinal()] = 2f;
        FLYING.defense[ROCK.ordinal()] = 2f;
        
        //GHOST
        GHOST.defense[FIGHTING.ordinal()] = 0.25f;
        GHOST.defense[NORMAL.ordinal()] = 0.25f;
        GHOST.defense[BUG.ordinal()] = 0.5f;
        GHOST.defense[POISON.ordinal()] = 0.5f;
        
        GHOST.defense[DARK.ordinal()] = 2f;
        GHOST.defense[GHOST.ordinal()] = 2f;
        
        //GRASS
        GRASS.defense[ELECTRIC.ordinal()] = 0.5f;
        GRASS.defense[GRASS.ordinal()] = 0.5f;
        GRASS.defense[GROUND.ordinal()] = 0.5f;
        GRASS.defense[WATER.ordinal()] = 0.5f;

        GRASS.defense[BUG.ordinal()] = 2f;
        GRASS.defense[FIRE.ordinal()] = 2f;
        GRASS.defense[FLYING.ordinal()] = 2f;
        GRASS.defense[ICE.ordinal()] = 2f;
        GRASS.defense[POISON.ordinal()] = 2f;
        
        //GROUND
        GROUND.defense[ELECTRIC.ordinal()] = 0.25f;
        GROUND.defense[POISON.ordinal()] = 0.5f;
        GROUND.defense[ROCK.ordinal()] = 0.5f;

        GROUND.defense[GRASS.ordinal()] = 2f;
        GROUND.defense[ICE.ordinal()] = 2f;
        GROUND.defense[WATER.ordinal()] = 2f;
        
        //ICE
        ICE.defense[ICE.ordinal()] = 0.5f;

        ICE.defense[FIGHTING.ordinal()] = 2f;
        ICE.defense[FIRE.ordinal()] = 2f;
        ICE.defense[ROCK.ordinal()] = 2f;
        ICE.defense[STEEL.ordinal()] = 2f;
        
        //NORMAL
        NORMAL.defense[GHOST.ordinal()] = 0.25f;
        NORMAL.defense[FIGHTING.ordinal()] = 2f;
        
        //POISON
        POISON.defense[BUG.ordinal()] = 0.5f;
        POISON.defense[FAIRY.ordinal()] = 0.5f;
        POISON.defense[FIRE.ordinal()] = 0.5f;
        POISON.defense[GRASS.ordinal()] = 0.5f;
        POISON.defense[FIGHTING.ordinal()] = 0.5f;

        POISON.defense[GROUND.ordinal()] = 2f;
        POISON.defense[PSYCHIC.ordinal()] = 2f;
        
        //ROCK
        ROCK.defense[FIRE.ordinal()] = 0.5f;
        ROCK.defense[FLYING.ordinal()] = 0.5f;
        ROCK.defense[NORMAL.ordinal()] = 0.5f;
        ROCK.defense[POISON.ordinal()] = 0.5f;

        ROCK.defense[FIGHTING.ordinal()] = 2f;
        ROCK.defense[GRASS.ordinal()] = 2f;
        ROCK.defense[GROUND.ordinal()] = 2f;
        ROCK.defense[STEEL.ordinal()] = 2f;
        ROCK.defense[WATER.ordinal()] = 2f;
        
        //STEEL
        STEEL.defense[POISON.ordinal()] = 0.25f;
        STEEL.defense[BUG.ordinal()] = 0.5f;
        STEEL.defense[DRAGON.ordinal()] = 0.5f;
        STEEL.defense[FAIRY.ordinal()] = 0.5f;
        STEEL.defense[FLYING.ordinal()] = 0.5f;
        STEEL.defense[GRASS.ordinal()] = 0.5f;
        STEEL.defense[ICE.ordinal()] = 0.5f;
        STEEL.defense[NORMAL.ordinal()] = 0.5f;
        STEEL.defense[PSYCHIC.ordinal()] = 0.5f;
        STEEL.defense[ROCK.ordinal()] = 0.5f;
        STEEL.defense[STEEL.ordinal()] = 0.5f;

        STEEL.defense[FIGHTING.ordinal()] = 2f;
        STEEL.defense[FIRE.ordinal()] = 2f;
        STEEL.defense[GROUND.ordinal()] = 2f;
        
        //WATER
        WATER.defense[FIRE.ordinal()] = 0.5f;
        WATER.defense[ICE.ordinal()] = 0.5f;
        WATER.defense[STEEL.ordinal()] = 0.5f;
        WATER.defense[WATER.ordinal()] = 0.5f;

        WATER.defense[ELECTRIC.ordinal()] = 2f;
        WATER.defense[GRASS.ordinal()] = 2f;
    }
    
    public float[] getEffectiveness() {
        return defense;
    }
    
    public float[] getDualEffect(Type t) {
        float[] dual = new float[18];
        
        for (int i = 0; i < 18; i++) {
            dual[i] = defense[i] * t.defense[i];
        }
        
        return dual;
    }
    
    public static Type valueOf(int i) { 
        switch(i) {
        case 0:
            return BUG;
        case 1:
            return DARK;
        case 2:
            return DRAGON;
        case 3:
            return ELECTRIC;
        case 4:
            return FAIRY;
        case 5:
            return FIGHTING;
        case 6:
            return FIRE;
        case 7:
            return FLYING;
        case 8:
            return GHOST;
        case 9:
            return GRASS;
        case 10:
            return GROUND;
        case 11:
            return ICE;
        case 12:
            return NORMAL;
        case 13:
            return POISON;
        case 14:
            return PSYCHIC;
        case 15:
            return ROCK;
        case 16:
            return STEEL;
        case 17:
            return WATER;
        }
        return null;
    }
    
}
