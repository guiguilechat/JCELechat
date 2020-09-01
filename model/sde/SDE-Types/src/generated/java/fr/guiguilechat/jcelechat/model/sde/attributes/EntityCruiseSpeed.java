package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The speed that entities fly at when not chasing a target.
 */
public class EntityCruiseSpeed
    extends RealAttribute
{
    public static final EntityCruiseSpeed INSTANCE = new EntityCruiseSpeed();

    @Override
    public int getId() {
        return  508;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntityCruiseSpeed";
    }
}
