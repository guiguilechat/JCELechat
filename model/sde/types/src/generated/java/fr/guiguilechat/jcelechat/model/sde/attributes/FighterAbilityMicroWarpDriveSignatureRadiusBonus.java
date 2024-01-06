package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Signature Radius Bonus
 */
public class FighterAbilityMicroWarpDriveSignatureRadiusBonus
    extends IntAttribute
{
    public static final FighterAbilityMicroWarpDriveSignatureRadiusBonus INSTANCE = new FighterAbilityMicroWarpDriveSignatureRadiusBonus();

    @Override
    public int getId() {
        return  2153;
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
        return "FighterAbilityMicroWarpDriveSignatureRadiusBonus";
    }
}
