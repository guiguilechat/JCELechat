package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Amarr Carrier skill level.
 */
public class ShipBonusForceAuxiliaryA1
    extends IntAttribute
{
    public final static ShipBonusForceAuxiliaryA1 INSTANCE = new ShipBonusForceAuxiliaryA1();

    @Override
    public int getId() {
        return  2308;
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
        return "ShipBonusForceAuxiliaryA1";
    }
}
