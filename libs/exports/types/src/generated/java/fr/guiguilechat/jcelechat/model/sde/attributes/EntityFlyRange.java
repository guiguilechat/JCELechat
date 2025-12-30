package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The distance at which the entity orbits, follows.. and more.
 */
public class EntityFlyRange
    extends RealAttribute
{
    public static final EntityFlyRange INSTANCE = new EntityFlyRange();

    @Override
    public int getId() {
        return  416;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  500.0;
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
        return "EntityFlyRange";
    }
}
