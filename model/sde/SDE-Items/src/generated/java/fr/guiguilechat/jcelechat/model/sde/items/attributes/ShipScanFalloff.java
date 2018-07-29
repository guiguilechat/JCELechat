package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Distance from maximum range at which accuracy has fallen by half.
 */
public class ShipScanFalloff
    extends IntAttribute
{
    public final static ShipScanFalloff INSTANCE = new ShipScanFalloff();

    @Override
    public int getId() {
        return  510;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipScanFalloff";
    }
}
