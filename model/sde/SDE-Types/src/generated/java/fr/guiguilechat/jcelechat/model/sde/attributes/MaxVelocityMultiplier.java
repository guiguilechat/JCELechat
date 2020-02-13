package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Maximum velocity multiplier
 */
public class MaxVelocityMultiplier
    extends DoubleAttribute
{
    public static final MaxVelocityMultiplier INSTANCE = new MaxVelocityMultiplier();

    @Override
    public int getId() {
        return  1470;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return "MaxVelocityMultiplier";
    }
}
