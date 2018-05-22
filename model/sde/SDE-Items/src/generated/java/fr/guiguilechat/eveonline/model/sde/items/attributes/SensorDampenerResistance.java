package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Resistance against Remote Sensor Dampeners.
 */
public class SensorDampenerResistance
    extends DoubleAttribute
{
    public final static SensorDampenerResistance INSTANCE = new SensorDampenerResistance();

    @Override
    public int getId() {
        return  2112;
    }

    @Override
    public int getCatId() {
        return  36;
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
