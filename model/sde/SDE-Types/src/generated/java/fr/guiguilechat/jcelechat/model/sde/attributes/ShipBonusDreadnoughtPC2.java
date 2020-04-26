package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Triglavian Dreadnought skill level
 */
public class ShipBonusDreadnoughtPC2
    extends IntAttribute
{
    public static final ShipBonusDreadnoughtPC2 INSTANCE = new ShipBonusDreadnoughtPC2();

    @Override
    public int getId() {
        return  2829;
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
        return "ShipBonusDreadnoughtPC2";
    }
}
