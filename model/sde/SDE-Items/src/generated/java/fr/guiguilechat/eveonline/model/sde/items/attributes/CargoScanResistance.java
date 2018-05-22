package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Chance of being able to resist a cargo scan.
 */
public class CargoScanResistance
    extends IntAttribute
{
    public final static CargoScanResistance INSTANCE = new CargoScanResistance();

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
