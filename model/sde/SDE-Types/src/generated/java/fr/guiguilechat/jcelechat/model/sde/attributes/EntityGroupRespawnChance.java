package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The chance an entity will respawn into his group if destroyed.
 */
public class EntityGroupRespawnChance
    extends IntAttribute
{
    public static final EntityGroupRespawnChance INSTANCE = new EntityGroupRespawnChance();

    @Override
    public int getId() {
        return  640;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "EntityGroupRespawnChance";
    }
}
