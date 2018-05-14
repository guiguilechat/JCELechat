package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Velocity multiplier for missiles
 */
public class MissileVelocityMultiplier
    extends DoubleAttribute
{
    public final static MissileVelocityMultiplier INSTANCE = new MissileVelocityMultiplier();

    @Override
    public int getId() {
        return  1469;
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
        return true;
    }
}
