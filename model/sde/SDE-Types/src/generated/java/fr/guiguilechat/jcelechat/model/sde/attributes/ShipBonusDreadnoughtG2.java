package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Dreadnought skill level
 */
public class ShipBonusDreadnoughtG2
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtG2 INSTANCE = new ShipBonusDreadnoughtG2();

    @Override
    public int getId() {
        return  2290;
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
        return "ShipBonusDreadnoughtG2";
    }
}
