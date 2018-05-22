package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
 */
public class ClothingAlsoCoversCategory2
    extends IntAttribute
{
    public final static ClothingAlsoCoversCategory2 INSTANCE = new ClothingAlsoCoversCategory2();

    @Override
    public int getId() {
        return  2058;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ClothingAlsoCoversCategory2";
    }
}
