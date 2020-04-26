package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class FighterAbilityAttackMissileReductionSensitivity
    extends DoubleAttribute
{
    public static final FighterAbilityAttackMissileReductionSensitivity INSTANCE = new FighterAbilityAttackMissileReductionSensitivity();

    @Override
    public int getId() {
        return  2232;
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
