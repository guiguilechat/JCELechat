package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Jump Range
 */
public class FighterAbilityMicroJumpDriveDistance
    extends IntAttribute
{
    public final static FighterAbilityMicroJumpDriveDistance INSTANCE = new FighterAbilityMicroJumpDriveDistance();

    @Override
    public int getId() {
        return  2154;
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
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityMicroJumpDriveDistance";
    }
}
