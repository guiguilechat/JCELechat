package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The required minimum military development index level
 */
public class DevIndexMilitary
    extends IntAttribute
{
    public static final DevIndexMilitary INSTANCE = new DevIndexMilitary();

    @Override
    public int getId() {
        return  1583;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DevIndexMilitary";
    }
}
