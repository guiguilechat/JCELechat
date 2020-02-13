package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus for Sensor Strength
 */
public class SensorStrengthBonus
    extends IntAttribute
{
    public static final SensorStrengthBonus INSTANCE = new SensorStrengthBonus();

    @Override
    public int getId() {
        return  1851;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "SensorStrengthBonus";
    }
}
