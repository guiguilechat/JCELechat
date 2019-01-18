package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusForceAuxiliaryG2
    extends DoubleAttribute
{
    public static final ShipBonusForceAuxiliaryG2 INSTANCE = new ShipBonusForceAuxiliaryG2();

    @Override
    public int getId() {
        return  2315;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "ShipBonusForceAuxiliaryG2";
    }
}
