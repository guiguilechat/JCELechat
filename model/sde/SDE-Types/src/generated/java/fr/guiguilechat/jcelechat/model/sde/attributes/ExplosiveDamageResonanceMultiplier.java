package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to the explosive damage resistance of something.
 */
public class ExplosiveDamageResonanceMultiplier
    extends RealAttribute
{
    public static final ExplosiveDamageResonanceMultiplier INSTANCE = new ExplosiveDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  132;
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
        return "ExplosiveDamageResonanceMultiplier";
    }
}
