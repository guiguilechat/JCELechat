package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Magnetometric ECM Jammer Strength
 */
public class FighterAbilityECMStrengthMagnetometric
    extends DoubleAttribute
{
    public final static FighterAbilityECMStrengthMagnetometric INSTANCE = new FighterAbilityECMStrengthMagnetometric();

    @Override
    public int getId() {
        return  2248;
    }

    @Override
    public int getCatId() {
        return  34;
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
        return "FighterAbilityECMStrengthMagnetometric";
    }
}
