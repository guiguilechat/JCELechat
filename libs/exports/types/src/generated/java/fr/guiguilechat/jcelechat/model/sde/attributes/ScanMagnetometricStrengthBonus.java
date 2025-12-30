package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * +/- modifier to the magnetometric strength of an electronic system.
 */
public class ScanMagnetometricStrengthBonus
    extends RealAttribute
{
    public static final ScanMagnetometricStrengthBonus INSTANCE = new ScanMagnetometricStrengthBonus();

    @Override
    public int getId() {
        return  240;
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
        return "ScanMagnetometricStrengthBonus";
    }
}
