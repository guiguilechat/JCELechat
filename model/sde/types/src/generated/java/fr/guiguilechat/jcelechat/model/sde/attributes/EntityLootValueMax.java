package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum value of any loot dropped by this entity.  Thats for each unit of any given item of loot, not for the total value of all items of loot dropped.
 */
public class EntityLootValueMax
    extends IntAttribute
{
    public static final EntityLootValueMax INSTANCE = new EntityLootValueMax();

    @Override
    public int getId() {
        return  249;
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
        return "EntityLootValueMax";
    }
}
