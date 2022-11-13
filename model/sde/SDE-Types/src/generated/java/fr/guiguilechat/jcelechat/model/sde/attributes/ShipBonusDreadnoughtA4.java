package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Amarr Dreadnought skill level
 */
public class ShipBonusDreadnoughtA4
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtA4 INSTANCE = new ShipBonusDreadnoughtA4();

    @Override
    public int getId() {
        return  5214;
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
        return "ShipBonusDreadnoughtA4";
    }
}
