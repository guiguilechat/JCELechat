package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Titan skill level.
 */
public class ShipBonusTitanG2
    extends IntAttribute
{
    public static final ShipBonusTitanG2 INSTANCE = new ShipBonusTitanG2();

    @Override
    public int getId() {
        return  2415;
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
        return "ShipBonusTitanG2";
    }
}
