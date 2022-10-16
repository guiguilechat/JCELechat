package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class HullKineticDamageResonance
    extends RealAttribute
{
    public static final HullKineticDamageResonance INSTANCE = new HullKineticDamageResonance();

    @Override
    public int getId() {
        return  976;
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
        return "HullKineticDamageResonance";
    }
}
