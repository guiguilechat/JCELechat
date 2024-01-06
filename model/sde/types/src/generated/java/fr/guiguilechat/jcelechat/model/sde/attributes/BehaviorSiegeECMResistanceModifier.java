package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BehaviorSiegeECMResistanceModifier
    extends RealAttribute
{
    public static final BehaviorSiegeECMResistanceModifier INSTANCE = new BehaviorSiegeECMResistanceModifier();

    @Override
    public int getId() {
        return  2642;
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
        return "BehaviorSiegeECMResistanceModifier";
    }
}
