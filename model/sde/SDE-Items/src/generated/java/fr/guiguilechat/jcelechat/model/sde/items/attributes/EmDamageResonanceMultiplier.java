package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplier to the EM damage resonance of something.
 */
public class EmDamageResonanceMultiplier
    extends DoubleAttribute
{
    public final static EmDamageResonanceMultiplier INSTANCE = new EmDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  133;
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
        return false;
    }

    @Override
    public String toString() {
        return "EmDamageResonanceMultiplier";
    }
}
