package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BoosterEffectChance4
    extends RealAttribute
{
    public static final BoosterEffectChance4 INSTANCE = new BoosterEffectChance4();

    @Override
    public int getId() {
        return  1092;
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
        return true;
    }

    @Override
    public String toString() {
        return "BoosterEffectChance4";
    }
}
