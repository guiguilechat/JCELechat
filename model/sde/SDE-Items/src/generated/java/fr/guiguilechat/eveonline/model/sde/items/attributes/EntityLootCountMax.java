package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The maximum number of pieces of loot dropped by this entity.
 */
public class EntityLootCountMax
    extends IntAttribute
{
    public final static EntityLootCountMax INSTANCE = new EntityLootCountMax();

    @Override
    public int getId() {
        return  251;
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
