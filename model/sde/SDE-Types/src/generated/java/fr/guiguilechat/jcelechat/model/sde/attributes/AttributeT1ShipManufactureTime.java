package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class AttributeT1ShipManufactureTime
    extends IntAttribute
{
    public static final AttributeT1ShipManufactureTime INSTANCE = new AttributeT1ShipManufactureTime();

    @Override
    public int getId() {
        return  2773;
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
        return true;
    }

    @Override
    public String toString() {
        return "AttributeT1ShipManufactureTime";
    }
}
