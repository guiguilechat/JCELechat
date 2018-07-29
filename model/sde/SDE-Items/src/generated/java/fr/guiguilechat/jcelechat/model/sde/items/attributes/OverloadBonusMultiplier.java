package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplier to all overload bonuses
 */
public class OverloadBonusMultiplier
    extends DoubleAttribute
{
    public final static OverloadBonusMultiplier INSTANCE = new OverloadBonusMultiplier();

    @Override
    public int getId() {
        return  1486;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return "OverloadBonusMultiplier";
    }
}
