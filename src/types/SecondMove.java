package types;

public enum SecondMove {
    NONE, BABY, NORMAL, PSEUDO, LEGEND;
    
    private int startdust;
    private int candy;

    static {
        NONE.startdust = 0;
        NONE.candy = 0;

        BABY.startdust = 10000;
        BABY.candy = 25;

        NORMAL.startdust = 50000;
        NORMAL.candy = 50;

        PSEUDO.startdust = 75000;
        PSEUDO.candy = 75;

        LEGEND.startdust = 100000;
        LEGEND.candy = 100;
    }
    
    public int getStartdust() {
        return startdust;
    }
    public int getCandy() {
        return candy;
    }
}
