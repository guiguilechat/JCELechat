package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Minmatar Dreadnought skill level
 */
public class ShipBonusDreadnoughtM2
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtM2 INSTANCE = new ShipBonusDreadnoughtM2();

    @Override
    public int getId() {
        return  2293;
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
        return "ShipBonusDreadnoughtM2";
    }
}
