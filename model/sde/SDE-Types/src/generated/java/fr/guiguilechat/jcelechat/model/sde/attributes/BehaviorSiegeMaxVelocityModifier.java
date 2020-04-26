package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorSiegeMaxVelocityModifier
    extends IntAttribute
{
    public static final BehaviorSiegeMaxVelocityModifier INSTANCE = new BehaviorSiegeMaxVelocityModifier();

    @Override
    public int getId() {
        return  2643;
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
        return "BehaviorSiegeMaxVelocityModifier";
    }
}
