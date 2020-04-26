package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Velocity of the damage cloud created on impact.
 */
public class AoeVelocity
    extends DoubleAttribute
{
    public static final AoeVelocity INSTANCE = new AoeVelocity();

    @Override
    public int getId() {
        return  653;
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
        return false;
    }

    @Override
    public String toString() {
        return "AoeVelocity";
    }
}
