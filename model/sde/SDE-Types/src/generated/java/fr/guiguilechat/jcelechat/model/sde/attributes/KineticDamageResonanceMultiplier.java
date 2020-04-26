package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to the kinetic damage resonance of something.
 */
public class KineticDamageResonanceMultiplier
    extends DoubleAttribute
{
    public static final KineticDamageResonanceMultiplier INSTANCE = new KineticDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  131;
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
        return "KineticDamageResonanceMultiplier";
    }
}
