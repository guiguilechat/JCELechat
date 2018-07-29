package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusForceAuxiliaryG4
    extends IntAttribute
{
    public final static ShipBonusForceAuxiliaryG4 INSTANCE = new ShipBonusForceAuxiliaryG4();

    @Override
    public int getId() {
        return  2322;
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
        return "ShipBonusForceAuxiliaryG4";
    }
}
