package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Bonus to fighter shield recharge (%)
 */
public class FighterBonusShieldRechargePercent
    extends DoubleAttribute
{
    public final static FighterBonusShieldRechargePercent INSTANCE = new FighterBonusShieldRechargePercent();

    @Override
    public int getId() {
        return  2338;
    }

    @Override
    public int getCatId() {
        return  38;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
