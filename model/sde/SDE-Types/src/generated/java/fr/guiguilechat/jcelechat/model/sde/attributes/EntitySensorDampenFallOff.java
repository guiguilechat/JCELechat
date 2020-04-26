package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Fall Off for NPC sensor dampen
 */
public class EntitySensorDampenFallOff
    extends IntAttribute
{
    public static final EntitySensorDampenFallOff INSTANCE = new EntitySensorDampenFallOff();

    @Override
    public int getId() {
        return  950;
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
        return "EntitySensorDampenFallOff";
    }
}
