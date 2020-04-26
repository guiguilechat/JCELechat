package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is the highest sensor strength in points for probes under the revised probing system
 */
public class BaseSensorStrength
    extends IntAttribute
{
    public static final BaseSensorStrength INSTANCE = new BaseSensorStrength();

    @Override
    public int getId() {
        return  1371;
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
        return false;
    }

    @Override
    public String toString() {
        return "BaseSensorStrength";
    }
}
