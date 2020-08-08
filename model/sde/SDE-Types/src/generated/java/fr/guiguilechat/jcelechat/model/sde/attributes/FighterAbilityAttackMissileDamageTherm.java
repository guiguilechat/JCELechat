package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Thermal Damage
 */
public class FighterAbilityAttackMissileDamageTherm
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileDamageTherm INSTANCE = new FighterAbilityAttackMissileDamageTherm();

    @Override
    public int getId() {
        return  2228;
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
        return "FighterAbilityAttackMissileDamageTherm";
    }
}
