package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Ladar ECM Jammer Strength
 */
public class FighterAbilityECMStrengthLadar
    extends RealAttribute
{
    public static final FighterAbilityECMStrengthLadar INSTANCE = new FighterAbilityECMStrengthLadar();

    @Override
    public int getId() {
        return  2247;
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
        return "FighterAbilityECMStrengthLadar";
    }
}
