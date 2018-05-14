package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Cargo Scanner Range Bonus
 */
public class CargoScannerRangeBonus
    extends IntAttribute
{
    public final static CargoScannerRangeBonus INSTANCE = new CargoScannerRangeBonus();

    @Override
    public int getId() {
        return  1235;
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
}
