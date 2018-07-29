package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Signature Radius Bonus
 */
public class FighterAbilityMicroWarpDriveSignatureRadiusBonus
    extends IntAttribute
{
    public final static FighterAbilityMicroWarpDriveSignatureRadiusBonus INSTANCE = new FighterAbilityMicroWarpDriveSignatureRadiusBonus();

    @Override
    public int getId() {
        return  2153;
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
        return "FighterAbilityMicroWarpDriveSignatureRadiusBonus";
    }
}
