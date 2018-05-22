package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * This is the radius that the depletion at this pin effects
 */
public class ExtractorDepletionRange
    extends IntAttribute
{
    public final static ExtractorDepletionRange INSTANCE = new ExtractorDepletionRange();

    @Override
    public int getId() {
        return  1644;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
