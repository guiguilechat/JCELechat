package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Titan skill level.
 */
public class ShipBonusTitanC3
    extends IntAttribute
{
    public static final ShipBonusTitanC3 INSTANCE = new ShipBonusTitanC3();

    @Override
    public int getId() {
        return  2412;
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
        return "ShipBonusTitanC3";
    }
}
