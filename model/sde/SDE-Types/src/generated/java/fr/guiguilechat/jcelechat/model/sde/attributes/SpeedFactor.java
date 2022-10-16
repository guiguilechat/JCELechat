package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Factor by which topspeed increases.
 */
public class SpeedFactor
    extends RealAttribute
{
    public static final SpeedFactor INSTANCE = new SpeedFactor();

    @Override
    public int getId() {
        return  20;
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
        return false;
    }

    @Override
    public String toString() {
        return "SpeedFactor";
    }
}
