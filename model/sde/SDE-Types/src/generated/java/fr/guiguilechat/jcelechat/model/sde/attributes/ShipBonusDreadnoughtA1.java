package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplied by Amarr Dreadnought skill level
 */
public class ShipBonusDreadnoughtA1
    extends RealAttribute
{
    public static final ShipBonusDreadnoughtA1 INSTANCE = new ShipBonusDreadnoughtA1();

    @Override
    public int getId() {
        return  2283;
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
        return "ShipBonusDreadnoughtA1";
    }
}
