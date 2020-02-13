package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class FighterAbilityAttackMissileReductionFactor
    extends DoubleAttribute
{
    public static final FighterAbilityAttackMissileReductionFactor INSTANCE = new FighterAbilityAttackMissileReductionFactor();

    @Override
    public int getId() {
        return  2231;
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
        return "FighterAbilityAttackMissileReductionFactor";
    }
}
