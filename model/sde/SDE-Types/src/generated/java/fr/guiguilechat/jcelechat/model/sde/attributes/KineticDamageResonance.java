package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * damage multiplier vs. kinetic damagers.
 */
public class KineticDamageResonance
    extends RealAttribute
{
    public static final KineticDamageResonance INSTANCE = new KineticDamageResonance();

    @Override
    public int getId() {
        return  109;
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
        return "KineticDamageResonance";
    }
}
