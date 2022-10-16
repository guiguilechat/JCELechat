package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityMicroJumpDriveDuration
    extends IntAttribute
{
    public static final FighterAbilityMicroJumpDriveDuration INSTANCE = new FighterAbilityMicroJumpDriveDuration();

    @Override
    public int getId() {
        return  2155;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "FighterAbilityMicroJumpDriveDuration";
    }
}
