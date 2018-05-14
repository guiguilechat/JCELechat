package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Reward for destroying this entity.
 */
public class EntityKillBounty
    extends IntAttribute
{
    public final static EntityKillBounty INSTANCE = new EntityKillBounty();

    @Override
    public int getId() {
        return  481;
    }

    @Override
    public int getCatId() {
        return  32;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
