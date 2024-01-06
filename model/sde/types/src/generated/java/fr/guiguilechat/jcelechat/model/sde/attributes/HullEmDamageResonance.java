package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class HullEmDamageResonance
    extends RealAttribute
{
    public static final HullEmDamageResonance INSTANCE = new HullEmDamageResonance();

    @Override
    public int getId() {
        return  974;
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
        return "HullEmDamageResonance";
    }
}
