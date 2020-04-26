package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Deprecated. The minimum number of pieces of loot dropped by this entity.
 */
public class EntityLootCountMin
    extends IntAttribute
{
    public static final EntityLootCountMin INSTANCE = new EntityLootCountMin();

    @Override
    public int getId() {
        return  250;
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
        return "EntityLootCountMin";
    }
}
