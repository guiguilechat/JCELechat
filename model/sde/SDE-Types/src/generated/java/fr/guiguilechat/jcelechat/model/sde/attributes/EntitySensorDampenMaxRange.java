package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Range from target for when the NPC activates the effect
 */
public class EntitySensorDampenMaxRange
    extends IntAttribute
{
    public static final EntitySensorDampenMaxRange INSTANCE = new EntitySensorDampenMaxRange();

    @Override
    public int getId() {
        return  938;
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
        return "EntitySensorDampenMaxRange";
    }
}
