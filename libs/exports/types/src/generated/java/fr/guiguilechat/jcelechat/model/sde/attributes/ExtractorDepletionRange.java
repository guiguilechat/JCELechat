package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is the radius that the depletion at this pin effects
 */
public class ExtractorDepletionRange
    extends IntAttribute
{
    public static final ExtractorDepletionRange INSTANCE = new ExtractorDepletionRange();

    @Override
    public int getId() {
        return  1644;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  10.0;
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
        return "ExtractorDepletionRange";
    }
}
