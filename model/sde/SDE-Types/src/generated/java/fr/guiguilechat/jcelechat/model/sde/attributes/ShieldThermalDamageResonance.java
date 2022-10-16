package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplies THERMAL damage taken by Shield. 
 */
public class ShieldThermalDamageResonance
    extends RealAttribute
{
    public static final ShieldThermalDamageResonance INSTANCE = new ShieldThermalDamageResonance();

    @Override
    public int getId() {
        return  274;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
