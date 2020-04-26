package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Missile FOF velocity percent
 */
public class MissileFOFVelocityPercent
    extends IntAttribute
{
    public static final MissileFOFVelocityPercent INSTANCE = new MissileFOFVelocityPercent();

    @Override
    public int getId() {
        return  406;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  100.0;
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
        return "MissileFOFVelocityPercent";
    }
}
