package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Maximum velocity multiplier
 */
public class MaxVelocityMultiplier
    extends RealAttribute
{
    public static final MaxVelocityMultiplier INSTANCE = new MaxVelocityMultiplier();

    @Override
    public int getId() {
        return  1470;
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
        return "MaxVelocityMultiplier";
    }
}
