package types;

public class ChargeMove extends Move {

    private int pvpPower;
    private int pvpEnergyReq;
    private String pvpEffect;
    private int effectPercent;
    
    public ChargeMove() {
        super();
        this.pvpPower = 0;
        this.pvpEnergyReq = 0;
        this.pvpEffect = null;
        this.effectPercent = 0;
    }

    public ChargeMove(String n, Type t, int pvpPower, int pvpEnergyReq) {
        super(n, t);
        this.pvpPower = pvpPower;
        this.pvpEnergyReq = pvpEnergyReq;
        this.pvpEffect = null;
        this.effectPercent = 0;
    }
    
    public ChargeMove(String n, Type t, int pvpPower, int pvpEnergyReq, String pvpEffect, int effectPercent) {
        super(n, t);
        this.pvpPower = pvpPower;
        this.pvpEnergyReq = pvpEnergyReq;
        this.pvpEffect = pvpEffect;
        this.effectPercent = effectPercent;
    }

    public int getPvpPower() {
        return pvpPower;
    }

    public int getPvpEnergyReq() {
        return pvpEnergyReq;
    }
    
    public boolean hasEffect() {
        return pvpEffect != null;
    }

    public String getPvpEffect() {
        return pvpEffect;
    }

    public int getEffectPercent() {
        return effectPercent;
    }

    public void setPvpPower(int pvpPower) {
        this.pvpPower = pvpPower;
    }

    public void setPvpEnergyReq(int pvpEnergyReq) {
        this.pvpEnergyReq = pvpEnergyReq;
    }

    public void setPvpEffect(String pvpEffect) {
        this.pvpEffect = pvpEffect;
    }

    public void setEffectPercent(int effectPercent) {
        this.effectPercent = effectPercent;
    }

}
