package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Damage Bonus for Cruise Missiles
 */
public class CruiseMissileVelocityPercent
    extends IntAttribute
{
    public static final CruiseMissileVelocityPercent INSTANCE = new CruiseMissileVelocityPercent();

    @Override
    public int getId() {
        return  401;
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
        return "CruiseMissileVelocityPercent";
    }
}
