package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusForceAuxiliaryG3
    extends IntAttribute
{
    public static final ShipBonusForceAuxiliaryG3 INSTANCE = new ShipBonusForceAuxiliaryG3();

    @Override
    public int getId() {
        return  2316;
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
        return "ShipBonusForceAuxiliaryG3";
    }
}
