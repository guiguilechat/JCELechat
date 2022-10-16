package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum Velocity Bonus
 */
public class FighterAbilityMicroWarpDriveSpeedBonus
    extends IntAttribute
{
    public static final FighterAbilityMicroWarpDriveSpeedBonus INSTANCE = new FighterAbilityMicroWarpDriveSpeedBonus();

    @Override
    public int getId() {
        return  2152;
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
        return "FighterAbilityMicroWarpDriveSpeedBonus";
    }
}
