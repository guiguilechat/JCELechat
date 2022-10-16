package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * damage multiplier vs. explosive damagers.
 */
public class ExplosiveDamageResonance
    extends RealAttribute
{
    public static final ExplosiveDamageResonance INSTANCE = new ExplosiveDamageResonance();

    @Override
    public int getId() {
        return  111;
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
        return "ExplosiveDamageResonance";
    }
}
