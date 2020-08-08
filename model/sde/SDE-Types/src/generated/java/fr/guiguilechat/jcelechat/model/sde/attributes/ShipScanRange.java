package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum range that something can be ship scanned from.
 */
public class ShipScanRange
    extends IntAttribute
{
    public static final ShipScanRange INSTANCE = new ShipScanRange();

    @Override
    public int getId() {
        return  125;
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
        return "ShipScanRange";
    }
}
