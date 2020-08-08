package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplies THERMAL damage taken by Armor. 
 */
public class ArmorThermalDamageResonance
    extends DoubleAttribute
{
    public static final ArmorThermalDamageResonance INSTANCE = new ArmorThermalDamageResonance();

    @Override
    public int getId() {
        return  270;
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
        return "ArmorThermalDamageResonance";
    }
}
