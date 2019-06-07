package types;

public class FastMove extends Move {

    private int pvpPower;
    private int pvpEnergy;
    private float pvpDuration;
    
    public FastMove() {
        super();
        this.pvpPower = 0;
        this.pvpEnergy = 0;
        this.pvpDuration = 0;
    }
    
    public FastMove(String n, Type t, int pvpPower, int pvpEnergy, float pvpDuration) {
        super(n, t);
        this.pvpPower = pvpPower;
        this.pvpEnergy = pvpEnergy;
        this.pvpDuration = pvpDuration;
    }

    public int getPvpPower() {
        return pvpPower;
    }

    public int getPvpEnergy() {
        return pvpEnergy;
    }

    public float getPvpDuration() {
        return pvpDuration;
    }

    public void setPvpPower(int pvpPower) {
        this.pvpPower = pvpPower;
    }

    public void setPvpEnergy(int pvpEnergy) {
        this.pvpEnergy = pvpEnergy;
    }

    public void setPvpDuration(float pvpDuration) {
        this.pvpDuration = pvpDuration;
    }

}
