package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
 */
public class ClothingRemovesCategory2
    extends IntAttribute
{
    public static final ClothingRemovesCategory2 INSTANCE = new ClothingRemovesCategory2();

    @Override
    public int getId() {
        return  2063;
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
        return "ClothingRemovesCategory2";
    }
}
