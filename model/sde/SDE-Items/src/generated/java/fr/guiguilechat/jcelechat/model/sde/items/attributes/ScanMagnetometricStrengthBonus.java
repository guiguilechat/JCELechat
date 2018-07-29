package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * +/- modifier to the magnetometric strength of an electronic system.
 */
public class ScanMagnetometricStrengthBonus
    extends DoubleAttribute
{
    public final static ScanMagnetometricStrengthBonus INSTANCE = new ScanMagnetometricStrengthBonus();

    @Override
    public int getId() {
        return  240;
    }

    @Override
    public int getCatId() {
        return  25;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanMagnetometricStrengthBonus";
    }
}
