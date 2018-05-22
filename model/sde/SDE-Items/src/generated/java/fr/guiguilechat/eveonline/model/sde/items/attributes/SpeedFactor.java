package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Factor by which topspeed increases.
 */
public class SpeedFactor
    extends DoubleAttribute
{
    public final static SpeedFactor INSTANCE = new SpeedFactor();

    @Override
    public int getId() {
        return  20;
    }

    @Override
    public int getCatId() {
        return  28;
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
        return false;
    }

    @Override
    public String toString() {
        return "SpeedFactor";
    }
}
