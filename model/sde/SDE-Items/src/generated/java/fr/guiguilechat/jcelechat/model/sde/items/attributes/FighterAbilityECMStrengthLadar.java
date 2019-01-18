package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Ladar ECM Jammer Strength
 */
public class FighterAbilityECMStrengthLadar
    extends DoubleAttribute
{
    public static final FighterAbilityECMStrengthLadar INSTANCE = new FighterAbilityECMStrengthLadar();

    @Override
    public int getId() {
        return  2247;
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
        return "FighterAbilityECMStrengthLadar";
    }
}
