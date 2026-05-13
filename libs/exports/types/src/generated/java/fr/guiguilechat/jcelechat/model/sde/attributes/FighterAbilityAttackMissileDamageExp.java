package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Explosive Damage
 */
public class FighterAbilityAttackMissileDamageExp
    extends RealAttribute
{
    public static final FighterAbilityAttackMissileDamageExp INSTANCE = new FighterAbilityAttackMissileDamageExp();

    @Override
    public int getId() {
        return  2230;
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
        return "FighterAbilityAttackMissileDamageExp";
    }
}
