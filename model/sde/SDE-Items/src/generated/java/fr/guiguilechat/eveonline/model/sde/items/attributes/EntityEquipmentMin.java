package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class EntityEquipmentMin
    extends IntAttribute
{
    public final static EntityEquipmentMin INSTANCE = new EntityEquipmentMin();

    @Override
    public int getId() {
        return  456;
    }

    @Override
    public int getCatId() {
        return  19;
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
}
