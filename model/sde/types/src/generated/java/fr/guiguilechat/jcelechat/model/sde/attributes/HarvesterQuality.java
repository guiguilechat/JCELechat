package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The quality of the material harvested.
 */
public class HarvesterQuality
    extends IntAttribute
{
    public static final HarvesterQuality INSTANCE = new HarvesterQuality();

    @Override
    public int getId() {
        return  710;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "HarvesterQuality";
    }
}
