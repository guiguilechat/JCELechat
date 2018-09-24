package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Missile FOF velocity percent
 */
public class MissileFOFVelocityPercent
    extends IntAttribute
{
    public final static MissileFOFVelocityPercent INSTANCE = new MissileFOFVelocityPercent();

    @Override
    public int getId() {
        return  406;
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
