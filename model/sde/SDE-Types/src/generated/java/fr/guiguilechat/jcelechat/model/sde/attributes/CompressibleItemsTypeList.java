package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The ID of a typelist describing which types of item this module can compress
 */
public class CompressibleItemsTypeList
    extends IntAttribute
{
    public static final CompressibleItemsTypeList INSTANCE = new CompressibleItemsTypeList();

    @Override
    public int getId() {
        return  3255;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
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
        return "CompressibleItemsTypeList";
    }
}
