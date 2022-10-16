package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Rate of fire
 */
public class FighterAbilityMissilesDuration
    extends IntAttribute
{
    public static final FighterAbilityMissilesDuration INSTANCE = new FighterAbilityMissilesDuration();

    @Override
    public int getId() {
        return  2182;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "FighterAbilityMissilesDuration";
    }
}
