package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Dreadnought skill level
 */
public class ShipBonusDreadnoughtC3
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtC3 INSTANCE = new ShipBonusDreadnoughtC3();

    @Override
    public int getId() {
        return  2288;
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
        return "ShipBonusDreadnoughtC3";
    }
}
