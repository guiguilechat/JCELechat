package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Damage Bonus for Cruise Missiles
 */
public class CruiseMissileVelocityPercent
    extends IntAttribute
{
    public final static CruiseMissileVelocityPercent INSTANCE = new CruiseMissileVelocityPercent();

    @Override
    public int getId() {
        return  401;
    }

    @Override
    public int getCatId() {
        return  9;
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
}
