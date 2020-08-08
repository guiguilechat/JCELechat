package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Magnetometric strength.
 */
public class ScanMagnetometricStrength
    extends DoubleAttribute
{
    public static final ScanMagnetometricStrength INSTANCE = new ScanMagnetometricStrength();

    @Override
    public int getId() {
        return  210;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanMagnetometricStrength";
    }
}
