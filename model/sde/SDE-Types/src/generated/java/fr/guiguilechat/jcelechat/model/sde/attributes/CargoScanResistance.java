package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance of being able to resist a cargo scan.
 */
public class CargoScanResistance
    extends RealAttribute
{
    public static final CargoScanResistance INSTANCE = new CargoScanResistance();

    @Override
    public int getId() {
        return  188;
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
        return "CargoScanResistance";
    }
}
