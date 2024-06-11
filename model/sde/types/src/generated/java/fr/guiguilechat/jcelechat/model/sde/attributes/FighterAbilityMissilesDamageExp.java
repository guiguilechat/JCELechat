package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Explosive Damage
 */
public class FighterAbilityMissilesDamageExp
    extends RealAttribute
{
    public static final FighterAbilityMissilesDamageExp INSTANCE = new FighterAbilityMissilesDamageExp();

    @Override
    public int getId() {
        return  2134;
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
        return "FighterAbilityMissilesDamageExp";
    }
}
