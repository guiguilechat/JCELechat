package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
 */
public class EmDamageResonance
    extends RealAttribute
{
    public static final EmDamageResonance INSTANCE = new EmDamageResonance();

    @Override
    public int getId() {
        return  113;
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
        return "EmDamageResonance";
    }
}
