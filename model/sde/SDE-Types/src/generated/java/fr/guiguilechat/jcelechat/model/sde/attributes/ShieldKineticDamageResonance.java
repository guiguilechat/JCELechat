package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplies KINETIC damage taken by Armor. 
 */
public class ShieldKineticDamageResonance
    extends DoubleAttribute
{
    public static final ShieldKineticDamageResonance INSTANCE = new ShieldKineticDamageResonance();

    @Override
    public int getId() {
        return  273;
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
        return "ShieldKineticDamageResonance";
    }
}
