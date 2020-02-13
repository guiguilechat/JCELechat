package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusForceAuxiliaryC4
    extends IntAttribute
{
    public static final ShipBonusForceAuxiliaryC4 INSTANCE = new ShipBonusForceAuxiliaryC4();

    @Override
    public int getId() {
        return  2321;
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
        return "ShipBonusForceAuxiliaryC4";
    }
}
