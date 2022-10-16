package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier for the missile's speed.
 */
public class MissileEntityVelocityMultiplier
    extends RealAttribute
{
    public static final MissileEntityVelocityMultiplier INSTANCE = new MissileEntityVelocityMultiplier();

    @Override
    public int getId() {
        return  645;
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
        return "MissileEntityVelocityMultiplier";
    }
}
