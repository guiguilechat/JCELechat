package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Caldari Dreadnought skill level
 */
public class ShipBonusDreadnoughtC2
    extends IntAttribute
{
    public final static ShipBonusDreadnoughtC2 INSTANCE = new ShipBonusDreadnoughtC2();

    @Override
    public int getId() {
        return  2287;
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
        return "ShipBonusDreadnoughtC2";
    }
}
