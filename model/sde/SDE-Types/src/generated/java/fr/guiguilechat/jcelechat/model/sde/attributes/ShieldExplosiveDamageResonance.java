package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplies EXPLOSIVE damage taken by Armor. 
 */
public class ShieldExplosiveDamageResonance
    extends DoubleAttribute
{
    public static final ShieldExplosiveDamageResonance INSTANCE = new ShieldExplosiveDamageResonance();

    @Override
    public int getId() {
        return  272;
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
        return "ShieldExplosiveDamageResonance";
    }
}
