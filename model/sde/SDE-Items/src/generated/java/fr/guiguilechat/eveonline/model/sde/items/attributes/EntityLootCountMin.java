package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Deprecated. The minimum number of pieces of loot dropped by this entity.
 */
public class EntityLootCountMin
    extends IntAttribute
{
    public final static EntityLootCountMin INSTANCE = new EntityLootCountMin();

    @Override
    public int getId() {
        return  250;
    }

    @Override
    public int getCatId() {
        return  19;
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
}
