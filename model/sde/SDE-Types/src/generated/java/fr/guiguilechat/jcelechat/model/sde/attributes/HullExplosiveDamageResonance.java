package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class HullExplosiveDamageResonance
    extends DoubleAttribute
{
    public static final HullExplosiveDamageResonance INSTANCE = new HullExplosiveDamageResonance();

    @Override
    public int getId() {
        return  975;
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
        return true;
    }

    @Override
    public String toString() {
        return "HullExplosiveDamageResonance";
    }
}
