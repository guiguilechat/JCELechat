package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class FighterAbilityMissilesDamageReductionSensitivity
    extends DoubleAttribute
{
    public static final FighterAbilityMissilesDamageReductionSensitivity INSTANCE = new FighterAbilityMissilesDamageReductionSensitivity();

    @Override
    public int getId() {
        return  2128;
    }

    @Override
    public int getCatId() {
        return  34;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "FighterAbilityMissilesDamageReductionSensitivity";
    }
}
