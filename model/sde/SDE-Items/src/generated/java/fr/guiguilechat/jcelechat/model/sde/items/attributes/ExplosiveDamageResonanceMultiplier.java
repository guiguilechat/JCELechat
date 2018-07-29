package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplier to the explosive damage resistance of something.
 */
public class ExplosiveDamageResonanceMultiplier
    extends DoubleAttribute
{
    public final static ExplosiveDamageResonanceMultiplier INSTANCE = new ExplosiveDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  132;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return true;
    }

    @Override
    public String toString() {
        return "ExplosiveDamageResonanceMultiplier";
    }
}
