package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Amarr Dreadnought skill level
 */
public class ShipBonusDreadnoughtA1
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtA1 INSTANCE = new ShipBonusDreadnoughtA1();

    @Override
    public int getId() {
        return  2283;
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
        return "ShipBonusDreadnoughtA1";
    }
}
