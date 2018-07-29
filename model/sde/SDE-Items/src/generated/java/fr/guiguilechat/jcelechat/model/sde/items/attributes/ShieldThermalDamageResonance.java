package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplies THERMAL damage taken by Shield. 
 */
public class ShieldThermalDamageResonance
    extends DoubleAttribute
{
    public final static ShieldThermalDamageResonance INSTANCE = new ShieldThermalDamageResonance();

    @Override
    public int getId() {
        return  274;
    }

    @Override
    public int getCatId() {
        return  2;
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
        return "ShieldThermalDamageResonance";
    }
}
