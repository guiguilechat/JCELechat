package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class AttributeT2ShipManufactureTime
    extends IntAttribute
{
    public static final AttributeT2ShipManufactureTime INSTANCE = new AttributeT2ShipManufactureTime();

    @Override
    public int getId() {
        return  2774;
    }

    @Override
    public int getCatId() {
        return  4;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "AttributeT2ShipManufactureTime";
    }
}
