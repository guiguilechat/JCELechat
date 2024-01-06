package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The range in m that the entity follows it's target.
 */
public class EntityFollowRange
    extends IntAttribute
{
    public static final EntityFollowRange INSTANCE = new EntityFollowRange();

    @Override
    public int getId() {
        return  257;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntityFollowRange";
    }
}
