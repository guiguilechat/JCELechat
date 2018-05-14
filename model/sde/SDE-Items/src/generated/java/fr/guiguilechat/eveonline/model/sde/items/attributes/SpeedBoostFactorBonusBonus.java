package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Modification of Afterburner and Microwarpdrive Thrust Bonus
 */
public class SpeedBoostFactorBonusBonus
    extends IntAttribute
{
    public final static SpeedBoostFactorBonusBonus INSTANCE = new SpeedBoostFactorBonusBonus();

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
}
