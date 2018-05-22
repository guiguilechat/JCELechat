package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * + / - modifier to a ship magnetometric strength
 */
public class ScanMagnetometricStrengthModifier
    extends IntAttribute
{
    public final static ScanMagnetometricStrengthModifier INSTANCE = new ScanMagnetometricStrengthModifier();

    @Override
    public int getId() {
        return  1568;
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
        return true;
    }

    @Override
    public String toString() {
        return "ScanMagnetometricStrengthModifier";
    }
}
