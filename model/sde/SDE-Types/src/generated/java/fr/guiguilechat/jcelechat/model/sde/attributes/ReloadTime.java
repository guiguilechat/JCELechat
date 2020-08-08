package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * reload time (ms)
 */
public class ReloadTime
    extends DoubleAttribute
{
    public static final ReloadTime INSTANCE = new ReloadTime();

    @Override
    public int getId() {
        return  1795;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  10000.0;
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
        return "ReloadTime";
    }
}
