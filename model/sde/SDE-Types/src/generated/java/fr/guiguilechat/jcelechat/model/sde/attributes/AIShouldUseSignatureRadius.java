package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Should this type use signature radius
 */
public class AIShouldUseSignatureRadius
    extends IntAttribute
{
    public static final AIShouldUseSignatureRadius INSTANCE = new AIShouldUseSignatureRadius();

    @Override
    public int getId() {
        return  1650;
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
        return "AIShouldUseSignatureRadius";
    }
}
