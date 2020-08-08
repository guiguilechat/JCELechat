package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Magnetometric ECM Jammer Strength
 */
public class FighterAbilityECMStrengthMagnetometric
    extends DoubleAttribute
{
    public static final FighterAbilityECMStrengthMagnetometric INSTANCE = new FighterAbilityECMStrengthMagnetometric();

    @Override
    public int getId() {
        return  2248;
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
        return "FighterAbilityECMStrengthMagnetometric";
    }
}
