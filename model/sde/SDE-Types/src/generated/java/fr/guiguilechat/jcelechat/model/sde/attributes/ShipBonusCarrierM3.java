package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplied by Minmatar Carrier skill level.
 */
public class ShipBonusCarrierM3
    extends DoubleAttribute
{
    public static final ShipBonusCarrierM3 INSTANCE = new ShipBonusCarrierM3();

    @Override
    public int getId() {
        return  2373;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ShipBonusCarrierM3";
    }
}
