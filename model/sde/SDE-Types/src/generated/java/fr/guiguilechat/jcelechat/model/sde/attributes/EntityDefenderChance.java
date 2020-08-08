package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * % chance of entity to shoot defender at incoming missile
 */
public class EntityDefenderChance
    extends DoubleAttribute
{
    public static final EntityDefenderChance INSTANCE = new EntityDefenderChance();

    @Override
    public int getId() {
        return  497;
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
        return "EntityDefenderChance";
    }
}
