package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Multiplied by Gallente Dreadnought skill level
 */
public class ShipBonusDreadnoughtG3
    extends IntAttribute
{
    public final static ShipBonusDreadnoughtG3 INSTANCE = new ShipBonusDreadnoughtG3();

    @Override
    public int getId() {
        return  2291;
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
