package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Caldari Titan skill level.
 */
public class ShipBonusTitanC1
    extends IntAttribute
{
    public final static ShipBonusTitanC1 INSTANCE = new ShipBonusTitanC1();

    @Override
    public int getId() {
        return  2410;
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
        return "ShipBonusTitanC1";
    }
}
