package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Minmatar Titan skill level.
 */
public class ShipBonusTitanM1
    extends IntAttribute
{
    public static final ShipBonusTitanM1 INSTANCE = new ShipBonusTitanM1();

    @Override
    public int getId() {
        return  2418;
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
        return "ShipBonusTitanM1";
    }
}
