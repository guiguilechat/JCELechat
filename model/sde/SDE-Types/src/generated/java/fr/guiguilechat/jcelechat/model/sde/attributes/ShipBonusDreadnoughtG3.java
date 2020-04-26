package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Dreadnought skill level
 */
public class ShipBonusDreadnoughtG3
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtG3 INSTANCE = new ShipBonusDreadnoughtG3();

    @Override
    public int getId() {
        return  2291;
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
        return "ShipBonusDreadnoughtG3";
    }
}
