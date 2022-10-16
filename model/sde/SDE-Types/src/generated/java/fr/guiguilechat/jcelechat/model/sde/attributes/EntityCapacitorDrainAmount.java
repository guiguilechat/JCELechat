package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of capacitor drained by NPC from target
 */
public class EntityCapacitorDrainAmount
    extends IntAttribute
{
    public static final EntityCapacitorDrainAmount INSTANCE = new EntityCapacitorDrainAmount();

    @Override
    public int getId() {
        return  946;
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
        return "EntityCapacitorDrainAmount";
    }
}
