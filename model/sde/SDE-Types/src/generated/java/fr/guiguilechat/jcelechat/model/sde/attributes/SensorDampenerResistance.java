package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Resistance against Remote Sensor Dampeners.
 */
public class SensorDampenerResistance
    extends DoubleAttribute
{
    public static final SensorDampenerResistance INSTANCE = new SensorDampenerResistance();

    @Override
    public int getId() {
        return  2112;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "SensorDampenerResistance";
    }
}
