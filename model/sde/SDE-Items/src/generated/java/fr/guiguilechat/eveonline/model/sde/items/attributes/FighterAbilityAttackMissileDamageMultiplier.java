package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Damage Multiplier
 */
public class FighterAbilityAttackMissileDamageMultiplier
    extends IntAttribute
{
    public final static FighterAbilityAttackMissileDamageMultiplier INSTANCE = new FighterAbilityAttackMissileDamageMultiplier();

    @Override
    public int getId() {
        return  2226;
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
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityAttackMissileDamageMultiplier";
    }
}
