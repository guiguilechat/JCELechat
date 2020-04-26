package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplied by Minmatar Carrier skill level.
 */
public class ShipBonusForceAuxiliaryM2
    extends DoubleAttribute
{
    public static final ShipBonusForceAuxiliaryM2 INSTANCE = new ShipBonusForceAuxiliaryM2();

    @Override
    public int getId() {
        return  2318;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipBonusForceAuxiliaryM2";
    }
}
