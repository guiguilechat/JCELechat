package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Modification of Afterburner and Microwarpdrive Thrust Bonus
 */
public class SpeedBoostFactorBonusBonus
    extends IntAttribute
{
    public static final SpeedBoostFactorBonusBonus INSTANCE = new SpeedBoostFactorBonusBonus();

    @Override
    public int getId() {
        return  1325;
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
        return "SpeedBoostFactorBonusBonus";
    }
}
