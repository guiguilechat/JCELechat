package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum number of pieces of loot dropped by this entity.
 */
public class EntityLootCountMax
    extends IntAttribute
{
    public static final EntityLootCountMax INSTANCE = new EntityLootCountMax();

    @Override
    public int getId() {
        return  251;
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
        return "EntityLootCountMax";
    }
}
