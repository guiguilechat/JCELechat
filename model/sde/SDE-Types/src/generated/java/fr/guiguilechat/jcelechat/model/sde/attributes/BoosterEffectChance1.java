package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class BoosterEffectChance1
    extends DoubleAttribute
{
    public static final BoosterEffectChance1 INSTANCE = new BoosterEffectChance1();

    @Override
    public int getId() {
        return  1089;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BoosterEffectChance1";
    }
}