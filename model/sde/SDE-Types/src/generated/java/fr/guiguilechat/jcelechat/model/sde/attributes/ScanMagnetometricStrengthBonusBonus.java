package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to Magnetometric Strength bonus
 */
public class ScanMagnetometricStrengthBonusBonus
    extends IntAttribute
{
    public static final ScanMagnetometricStrengthBonusBonus INSTANCE = new ScanMagnetometricStrengthBonusBonus();

    @Override
    public int getId() {
        return  2074;
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
        return true;
    }

    @Override
    public String toString() {
        return "ScanMagnetometricStrengthBonusBonus";
    }
}
