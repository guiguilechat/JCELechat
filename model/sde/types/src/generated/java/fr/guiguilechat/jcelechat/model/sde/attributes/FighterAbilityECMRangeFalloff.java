package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Effectiveness Falloff
 */
public class FighterAbilityECMRangeFalloff
    extends RealAttribute
{
    public static final FighterAbilityECMRangeFalloff INSTANCE = new FighterAbilityECMRangeFalloff();

    @Override
    public int getId() {
        return  2222;
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
        return "FighterAbilityECMRangeFalloff";
    }
}
