package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * + / - modifier to a ship magnetometric strength
 */
public class ScanMagnetometricStrengthModifier
    extends IntAttribute
{
    public static final ScanMagnetometricStrengthModifier INSTANCE = new ScanMagnetometricStrengthModifier();

    @Override
    public int getId() {
        return  1568;
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
        return "ScanMagnetometricStrengthModifier";
    }
}
