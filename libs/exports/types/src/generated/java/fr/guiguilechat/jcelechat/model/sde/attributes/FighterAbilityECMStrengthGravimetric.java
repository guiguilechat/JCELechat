package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Gravimetric ECM Jammer Strength
 */
public class FighterAbilityECMStrengthGravimetric
    extends RealAttribute
{
    public static final FighterAbilityECMStrengthGravimetric INSTANCE = new FighterAbilityECMStrengthGravimetric();

    @Override
    public int getId() {
        return  2246;
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
        return "FighterAbilityECMStrengthGravimetric";
    }
}
