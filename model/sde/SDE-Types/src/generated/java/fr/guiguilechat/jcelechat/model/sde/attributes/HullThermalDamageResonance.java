package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class HullThermalDamageResonance
    extends DoubleAttribute
{
    public static final HullThermalDamageResonance INSTANCE = new HullThermalDamageResonance();

    @Override
    public int getId() {
        return  977;
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
        return "HullThermalDamageResonance";
    }
}