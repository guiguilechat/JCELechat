package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The minimum value of any given unit of loot dropped by this entity.  Not the minimum value of all the loot, but of any given item dropped.
 */
public class EntityLootValueMin
    extends IntAttribute
{
    public static final EntityLootValueMin INSTANCE = new EntityLootValueMin();

    @Override
    public int getId() {
        return  248;
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
        return "EntityLootValueMin";
    }
}
