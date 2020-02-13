package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Titan skill level.
 */
public class ShipBonusTitanG1
    extends IntAttribute
{
    public static final ShipBonusTitanG1 INSTANCE = new ShipBonusTitanG1();

    @Override
    public int getId() {
        return  2414;
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
        return "ShipBonusTitanG1";
    }
}
