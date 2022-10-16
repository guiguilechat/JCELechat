package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Damage Multiplier
 */
public class FighterAbilityMissilesDamageMultiplier
    extends IntAttribute
{
    public static final FighterAbilityMissilesDamageMultiplier INSTANCE = new FighterAbilityMissilesDamageMultiplier();

    @Override
    public int getId() {
        return  2130;
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
        return "FighterAbilityMissilesDamageMultiplier";
    }
}
