package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusCarrierG3
    extends DoubleAttribute
{
    public static final ShipBonusCarrierG3 INSTANCE = new ShipBonusCarrierG3();

    @Override
    public int getId() {
        return  2369;
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
        return "ShipBonusCarrierG3";
    }
}
