package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Multiplied by Gallente Titan skill level.
 */
public class ShipBonusTitanG3
    extends IntAttribute
{
    public final static ShipBonusTitanG3 INSTANCE = new ShipBonusTitanG3();

    @Override
    public int getId() {
        return  2416;
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
}
