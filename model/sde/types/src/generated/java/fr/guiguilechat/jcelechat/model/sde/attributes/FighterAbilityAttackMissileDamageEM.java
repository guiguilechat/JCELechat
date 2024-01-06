package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * EM Damage
 */
public class FighterAbilityAttackMissileDamageEM
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileDamageEM INSTANCE = new FighterAbilityAttackMissileDamageEM();

    @Override
    public int getId() {
        return  2227;
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
        return "FighterAbilityAttackMissileDamageEM";
    }
}
