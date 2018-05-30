package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class AttributeT1ShipManufactureTime
    extends IntAttribute
{
    public final static AttributeT1ShipManufactureTime INSTANCE = new AttributeT1ShipManufactureTime();

    @Override
    public int getId() {
        return  2773;
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
        return "AttributeT1ShipManufactureTime";
    }
}
