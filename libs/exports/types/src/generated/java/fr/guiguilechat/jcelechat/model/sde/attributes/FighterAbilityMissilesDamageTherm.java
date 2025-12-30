package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Thermal Damage
 */
public class FighterAbilityMissilesDamageTherm
    extends IntAttribute
{
    public static final FighterAbilityMissilesDamageTherm INSTANCE = new FighterAbilityMissilesDamageTherm();

    @Override
    public int getId() {
        return  2132;
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
        return "FighterAbilityMissilesDamageTherm";
    }
}
