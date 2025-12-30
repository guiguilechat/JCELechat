package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distance from maximum range at which accuracy has fallen by half.
 */
public class ShipScanFalloff
    extends IntAttribute
{
    public static final ShipScanFalloff INSTANCE = new ShipScanFalloff();

    @Override
    public int getId() {
        return  510;
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
