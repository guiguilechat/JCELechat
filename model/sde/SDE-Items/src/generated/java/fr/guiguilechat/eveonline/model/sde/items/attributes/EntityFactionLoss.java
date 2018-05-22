package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class EntityFactionLoss
    extends IntAttribute
{
    public final static EntityFactionLoss INSTANCE = new EntityFactionLoss();

    @Override
    public int getId() {
        return  562;
    }

    @Override
    public int getCatId() {
        return  32;
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
        return "EntityFactionLoss";
    }
}
