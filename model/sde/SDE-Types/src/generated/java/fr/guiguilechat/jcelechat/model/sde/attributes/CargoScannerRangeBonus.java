package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Cargo Scanner Range Bonus
 */
public class CargoScannerRangeBonus
    extends IntAttribute
{
    public static final CargoScannerRangeBonus INSTANCE = new CargoScannerRangeBonus();

    @Override
    public int getId() {
        return  1235;
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
        return "CargoScannerRangeBonus";
    }
}
