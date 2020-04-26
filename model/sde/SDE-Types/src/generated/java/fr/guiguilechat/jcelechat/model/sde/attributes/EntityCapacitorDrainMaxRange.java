package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Range for NPC capacitor drain
 */
public class EntityCapacitorDrainMaxRange
    extends IntAttribute
{
    public static final EntityCapacitorDrainMaxRange INSTANCE = new EntityCapacitorDrainMaxRange();

    @Override
    public int getId() {
        return  937;
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
        return "EntityCapacitorDrainMaxRange";
    }
}
