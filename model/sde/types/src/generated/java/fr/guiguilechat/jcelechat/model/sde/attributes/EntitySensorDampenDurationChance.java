package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance of NPC effect to be activated each duration
 */
public class EntitySensorDampenDurationChance
    extends RealAttribute
{
    public static final EntitySensorDampenDurationChance INSTANCE = new EntitySensorDampenDurationChance();

    @Override
    public int getId() {
        return  932;
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
        return "EntitySensorDampenDurationChance";
    }
}
