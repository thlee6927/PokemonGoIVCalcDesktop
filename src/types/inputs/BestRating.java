package types.inputs;

public enum BestRating {
    BEST, GREAT, GOOD, BAD;
    
    public int ivFloor;
    public int ivCeil;
    
    static {
        BEST.ivFloor = 15;
        BEST.ivCeil = 15;
        
        GREAT.ivFloor = 13;
        GREAT.ivCeil = 14;
        
        GOOD.ivFloor = 8;
        GOOD.ivCeil = 12;
        
        BAD.ivFloor = 0;
        BAD.ivCeil = 7;
    }
    
    public BestRating getRating(String s) {
        if (s.equals("0")) {
            return BEST;
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
        return null;
    }
}
