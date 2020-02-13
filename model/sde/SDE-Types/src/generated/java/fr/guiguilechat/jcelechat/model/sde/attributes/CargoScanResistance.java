package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Chance of being able to resist a cargo scan.
 */
public class CargoScanResistance
    extends IntAttribute
{
    public static final CargoScanResistance INSTANCE = new CargoScanResistance();

    @Override
    public int getId() {
        return  188;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "CargoScanResistance";
    }
}
