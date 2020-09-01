package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Affects the velocity of the target in missile impact calculations.
 */
public class MissileEntityAoeVelocityMultiplier
    extends RealAttribute
{
    public static final MissileEntityAoeVelocityMultiplier INSTANCE = new MissileEntityAoeVelocityMultiplier();

    @Override
    public int getId() {
        return  859;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "MissileEntityAoeVelocityMultiplier";
    }
}
