package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplied by Gallente Dreadnought skill level
 */
public class ShipBonusDreadnoughtG4
    extends RealAttribute
{
    public static final ShipBonusDreadnoughtG4 INSTANCE = new ShipBonusDreadnoughtG4();

    @Override
    public int getId() {
        return  5215;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ShipBonusDreadnoughtG4";
    }
}
