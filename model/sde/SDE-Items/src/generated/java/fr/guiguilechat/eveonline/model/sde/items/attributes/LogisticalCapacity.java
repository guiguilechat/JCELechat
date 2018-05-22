package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Transport capacity (bandwidth) in m3 per hour.
 */
public class LogisticalCapacity
    extends IntAttribute
{
    public final static LogisticalCapacity INSTANCE = new LogisticalCapacity();

    @Override
    public int getId() {
        return  1631;
    }

    @Override
    public int getCatId() {
        return  0;
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

    @Override
    public String toString() {
        return "LogisticalCapacity";
    }
}
