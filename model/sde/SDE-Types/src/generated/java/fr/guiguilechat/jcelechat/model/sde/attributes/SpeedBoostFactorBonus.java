package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Afterburner and Microwarpdrive Thrust Bonus
 */
public class SpeedBoostFactorBonus
    extends IntAttribute
{
    public static final SpeedBoostFactorBonus INSTANCE = new SpeedBoostFactorBonus();

    @Override
    public int getId() {
        return  1270;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SpeedBoostFactorBonus";
    }
}
