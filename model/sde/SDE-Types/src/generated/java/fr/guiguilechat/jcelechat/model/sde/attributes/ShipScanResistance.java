package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Chance of being able to resist a ship scan.
 */
public class ShipScanResistance
    extends IntAttribute
{
    public static final ShipScanResistance INSTANCE = new ShipScanResistance();

    @Override
    public int getId() {
        return  511;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipScanResistance";
    }
}
