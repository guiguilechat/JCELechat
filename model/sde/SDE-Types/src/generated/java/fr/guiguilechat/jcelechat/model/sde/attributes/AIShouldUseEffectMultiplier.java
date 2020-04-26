package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Should the entity watch for effects when choosing targets
 */
public class AIShouldUseEffectMultiplier
    extends IntAttribute
{
    public static final AIShouldUseEffectMultiplier INSTANCE = new AIShouldUseEffectMultiplier();

    @Override
    public int getId() {
        return  1652;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AIShouldUseEffectMultiplier";
    }
}
