package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Jump Range
 */
public class FighterAbilityMicroJumpDriveDistance
    extends IntAttribute
{
    public static final FighterAbilityMicroJumpDriveDistance INSTANCE = new FighterAbilityMicroJumpDriveDistance();

    @Override
    public int getId() {
        return  2154;
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
        return "FighterAbilityMicroJumpDriveDistance";
    }
}
