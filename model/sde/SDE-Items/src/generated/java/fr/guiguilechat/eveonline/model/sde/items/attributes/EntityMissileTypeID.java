package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The type of missiles the entity launches.
 */
public class EntityMissileTypeID
    extends IntAttribute
{
    public final static EntityMissileTypeID INSTANCE = new EntityMissileTypeID();

    @Override
    public int getId() {
        return  507;
    }

    @Override
    public int getCatId() {
        return  30;
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
        return "EntityMissileTypeID";
    }
}
