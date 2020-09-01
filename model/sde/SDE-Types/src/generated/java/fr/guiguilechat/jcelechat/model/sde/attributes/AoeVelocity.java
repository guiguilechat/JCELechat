package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Velocity of the damage cloud created on impact.
 */
public class AoeVelocity
    extends RealAttribute
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
    public Number getDefaultValue() {
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
