package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
 */
public class ClothingAlsoCoversCategory
    extends IntAttribute
{
    public static final ClothingAlsoCoversCategory INSTANCE = new ClothingAlsoCoversCategory();

    @Override
    public int getId() {
        return  1797;
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
        return "ClothingAlsoCoversCategory";
    }
}
