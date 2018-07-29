package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class FighterAbilityAttackMissileReductionSensitivity
    extends DoubleAttribute
{
    public final static FighterAbilityAttackMissileReductionSensitivity INSTANCE = new FighterAbilityAttackMissileReductionSensitivity();

    @Override
    public int getId() {
        return  2232;
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
        return "FighterAbilityAttackMissileReductionSensitivity";
    }
}
