package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Velocity of the damage cloud created on impact.
 */
public class AoeVelocity
    extends DoubleAttribute
{
    public final static AoeVelocity INSTANCE = new AoeVelocity();

    @Override
    public int getId() {
        return  653;
    }

    @Override
    public int getCatId() {
        return  7;
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
