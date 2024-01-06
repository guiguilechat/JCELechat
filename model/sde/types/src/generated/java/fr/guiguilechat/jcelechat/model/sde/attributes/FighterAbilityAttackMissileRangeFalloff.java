package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Accuracy Falloff
 */
public class FighterAbilityAttackMissileRangeFalloff
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileRangeFalloff INSTANCE = new FighterAbilityAttackMissileRangeFalloff();

    @Override
    public int getId() {
        return  2237;
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
        return "FighterAbilityAttackMissileRangeFalloff";
    }
}
