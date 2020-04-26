package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * damage multiplier vs. thermal.
 */
public class ThermalDamageResonance
    extends DoubleAttribute
{
    public static final ThermalDamageResonance INSTANCE = new ThermalDamageResonance();

    @Override
    public int getId() {
        return  110;
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
        return false;
    }

    @Override
    public String toString() {
        return "ThermalDamageResonance";
    }
}
