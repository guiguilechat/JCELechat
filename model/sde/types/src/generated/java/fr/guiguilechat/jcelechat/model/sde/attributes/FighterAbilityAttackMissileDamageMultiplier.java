package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Damage Multiplier
 */
public class FighterAbilityAttackMissileDamageMultiplier
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileDamageMultiplier INSTANCE = new FighterAbilityAttackMissileDamageMultiplier();

    @Override
    public int getId() {
        return  2226;
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
        return "FighterAbilityAttackMissileDamageMultiplier";
    }
}
