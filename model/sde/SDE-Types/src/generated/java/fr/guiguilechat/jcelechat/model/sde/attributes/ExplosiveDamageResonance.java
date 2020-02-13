package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * damage multiplier vs. explosive damagers.
 */
public class ExplosiveDamageResonance
    extends DoubleAttribute
{
    public static final ExplosiveDamageResonance INSTANCE = new ExplosiveDamageResonance();

    @Override
    public int getId() {
        return  111;
    }

    @Override
    public int getCatId() {
        return  4;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
