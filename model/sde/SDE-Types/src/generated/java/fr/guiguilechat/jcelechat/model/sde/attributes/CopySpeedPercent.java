package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Blueprint copying speed
 */
public class CopySpeedPercent
    extends IntAttribute
{
    public static final CopySpeedPercent INSTANCE = new CopySpeedPercent();

    @Override
    public int getId() {
        return  387;
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
        return true;
    }

    @Override
    public String toString() {
        return "CopySpeedPercent";
    }
}
