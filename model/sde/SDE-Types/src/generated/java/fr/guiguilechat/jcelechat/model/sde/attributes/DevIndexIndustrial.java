package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The minimum required industrial development index level
 */
public class DevIndexIndustrial
    extends IntAttribute
{
    public static final DevIndexIndustrial INSTANCE = new DevIndexIndustrial();

    @Override
    public int getId() {
        return  1584;
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
        return "DevIndexIndustrial";
    }
}
