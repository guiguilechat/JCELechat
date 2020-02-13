package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusForceAuxiliaryG4
    extends IntAttribute
{
    public static final ShipBonusForceAuxiliaryG4 INSTANCE = new ShipBonusForceAuxiliaryG4();

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
