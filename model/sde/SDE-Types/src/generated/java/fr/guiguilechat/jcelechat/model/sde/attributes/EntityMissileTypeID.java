package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The type of missiles the entity launches.
 */
public class EntityMissileTypeID
    extends IntAttribute
{
    public static final EntityMissileTypeID INSTANCE = new EntityMissileTypeID();

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
