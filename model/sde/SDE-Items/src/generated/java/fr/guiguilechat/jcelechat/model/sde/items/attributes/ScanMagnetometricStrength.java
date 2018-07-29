package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Magnetometric strength.
 */
public class ScanMagnetometricStrength
    extends DoubleAttribute
{
    public final static ScanMagnetometricStrength INSTANCE = new ScanMagnetometricStrength();

    @Override
    public int getId() {
        return  210;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "ScanMagnetometricStrength";
    }
}
