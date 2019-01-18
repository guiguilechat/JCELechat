package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


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
        return "FighterAbilityMicroWarpDriveSpeedBonus";
    }
}
