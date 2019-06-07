package types.inputs;

public enum IVRating {

    EXCELLENT, GREAT, GOOD, BAD, NONE;
    
    public int ivFloor;
    public int ivCeil;
    
    static {
        EXCELLENT.ivFloor = 80;
        EXCELLENT.ivCeil = 100;
        
        GREAT.ivFloor = 65;
        GREAT.ivCeil = 80;
        
        GOOD.ivFloor = 50;
        GOOD.ivCeil = 65;
        
        BAD.ivFloor = 0;
        BAD.ivCeil = 50;
        
        NONE.ivFloor = 0;
        NONE.ivCeil = 100;
    }
    
    public IVRating getRating(String s) {
        if (s.equals("0")) {
            return EXCELLENT;
        }
        if (s.equals("1")) {
            return GREAT;
        }
        if (s.equals("2")) {
            return GOOD;
        }
        if (s.equals("3")) {
            return BAD;
        }
        return NONE;
    }
    
}
