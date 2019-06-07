package types.inputs;

public enum EncounterType {
    WILD, GOOD, GREAT, ULTRA, WEATHER, BEST, RAID, LUCKY;
    
    private int ivFloor;
    
    static {
        WILD.ivFloor = 0;
        GOOD.ivFloor = 1;
        GREAT.ivFloor = 2;
        ULTRA.ivFloor = 3;
        WEATHER.ivFloor = 4;
        BEST.ivFloor = 5;
        RAID.ivFloor = 10;
        LUCKY.ivFloor = 12;
    }
    
    public int getIvFloor() {
        return ivFloor;
    }
    
    public String toString() {
        String s = ivFloor + ": " + name().charAt(0) + name().substring(1).toLowerCase();
        switch (this.ordinal()) {
        case 0:
        case 4:
            s+= " Catch";
            break;
        case 1:
        case 2:
        case 3:
        case 5:
            s+= " Friend";
            break;
        case 6:
            s+= "/Hatch/Quest";
            break;
        case 7:
            s+= " Trade";
        }
        return s;
    }
}
