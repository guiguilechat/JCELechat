package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * modifier to an entity capacitor level to represent energy drain for large ships
 */
public class EntityCapacitorLevelModifierLarge
    extends DoubleAttribute
{
    public final static EntityCapacitorLevelModifierLarge INSTANCE = new EntityCapacitorLevelModifierLarge();

    @Override
    public int getId() {
        return  1897;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "EntityCapacitorLevelModifierLarge";
    }
}
