package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Number of items needed to be able to compress it
 */
public class CompressionQuantityNeeded
    extends IntAttribute
{
    public final static CompressionQuantityNeeded INSTANCE = new CompressionQuantityNeeded();

    @Override
    public int getId() {
        return  1941;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CompressionQuantityNeeded";
    }
}
