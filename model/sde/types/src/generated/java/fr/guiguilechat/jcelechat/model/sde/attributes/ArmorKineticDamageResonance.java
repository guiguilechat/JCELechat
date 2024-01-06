package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplies KINETIC damage taken by Armor. 
 */
public class ArmorKineticDamageResonance
    extends RealAttribute
{
    public static final ArmorKineticDamageResonance INSTANCE = new ArmorKineticDamageResonance();

    @Override
    public int getId() {
        return  269;
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
        return "ArmorKineticDamageResonance";
    }
}
