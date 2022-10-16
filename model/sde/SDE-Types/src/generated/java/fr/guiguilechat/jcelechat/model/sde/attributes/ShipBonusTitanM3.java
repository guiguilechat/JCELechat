package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Minmatar Titan skill level.
 */
public class ShipBonusTitanM3
    extends IntAttribute
{
    public static final ShipBonusTitanM3 INSTANCE = new ShipBonusTitanM3();

    @Override
    public int getId() {
        return  2420;
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
        return "ShipBonusTitanM3";
    }
}
