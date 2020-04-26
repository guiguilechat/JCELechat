package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class BoosterEffectChance5
    extends DoubleAttribute
{
    public static final BoosterEffectChance5 INSTANCE = new BoosterEffectChance5();

    @Override
    public int getId() {
        return  1093;
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
        return true;
    }

    @Override
    public String toString() {
        return "BoosterEffectChance5";
    }
}
