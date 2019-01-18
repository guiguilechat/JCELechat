package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Signature Radius Bonus
 */
public class FighterAbilityMicroJumpDriveSignatureRadiusBonus
    extends IntAttribute
{
    public static final FighterAbilityMicroJumpDriveSignatureRadiusBonus INSTANCE = new FighterAbilityMicroJumpDriveSignatureRadiusBonus();

    @Override
    public int getId() {
        return  2156;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityMicroJumpDriveSignatureRadiusBonus";
    }
}
