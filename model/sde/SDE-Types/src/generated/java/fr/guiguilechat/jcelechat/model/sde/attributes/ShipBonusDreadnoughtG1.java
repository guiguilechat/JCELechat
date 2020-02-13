package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Dreadnought skill level
 */
public class ShipBonusDreadnoughtG1
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtG1 INSTANCE = new ShipBonusDreadnoughtG1();

    @Override
    public int getId() {
        return  2289;
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
        return "ShipBonusDreadnoughtG1";
    }
}
