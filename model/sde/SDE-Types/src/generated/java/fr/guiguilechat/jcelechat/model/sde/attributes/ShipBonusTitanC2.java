package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Titan skill level.
 */
public class ShipBonusTitanC2
    extends IntAttribute
{
    public static final ShipBonusTitanC2 INSTANCE = new ShipBonusTitanC2();

    @Override
    public int getId() {
        return  2411;
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
        return "ShipBonusTitanC2";
    }
}
