package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to the EM damage resonance of something.
 */
public class EmDamageResonanceMultiplier
    extends RealAttribute
{
    public static final EmDamageResonanceMultiplier INSTANCE = new EmDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  133;
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
        return "EmDamageResonanceMultiplier";
    }
}
