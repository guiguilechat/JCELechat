package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum range the cargo of a ship can be scanned from.
 */
public class CargoScanRange
    extends IntAttribute
{
    public static final CargoScanRange INSTANCE = new CargoScanRange();

    @Override
    public int getId() {
        return  126;
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
        return "CargoScanRange";
    }
}
