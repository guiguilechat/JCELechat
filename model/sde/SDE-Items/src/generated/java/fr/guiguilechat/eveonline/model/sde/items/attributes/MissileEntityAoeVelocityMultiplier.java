package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Affects the velocity of the target in missile impact calculations.
 */
public class MissileEntityAoeVelocityMultiplier
    extends DoubleAttribute
{
    public final static MissileEntityAoeVelocityMultiplier INSTANCE = new MissileEntityAoeVelocityMultiplier();

    @Override
    public int getId() {
        return  859;
    }

    @Override
    public int getCatId() {
        return  30;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
}
