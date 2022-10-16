package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class FighterAbilityMissilesDamageReductionFactor
    extends RealAttribute
{
    public static final FighterAbilityMissilesDamageReductionFactor INSTANCE = new FighterAbilityMissilesDamageReductionFactor();

    @Override
    public int getId() {
        return  2127;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityMissilesDamageReductionFactor";
    }
}
