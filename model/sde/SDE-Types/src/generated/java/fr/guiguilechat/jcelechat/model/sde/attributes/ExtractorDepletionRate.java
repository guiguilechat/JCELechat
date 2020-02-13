package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is the amount that is added to the depletion of a resource on a planet
 */
public class ExtractorDepletionRate
    extends IntAttribute
{
    public static final ExtractorDepletionRate INSTANCE = new ExtractorDepletionRate();

    @Override
    public int getId() {
        return  1645;
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
        return "ExtractorDepletionRate";
    }
}
