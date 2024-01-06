package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reward for destroying this entity.
 */
public class EntityKillBounty
    extends IntAttribute
{
    public static final EntityKillBounty INSTANCE = new EntityKillBounty();

    @Override
    public int getId() {
        return  481;
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
        return "EntityKillBounty";
    }
}
