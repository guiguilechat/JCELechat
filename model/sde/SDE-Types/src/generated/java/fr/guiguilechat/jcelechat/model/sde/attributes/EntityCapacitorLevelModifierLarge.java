package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * modifier to an entity capacitor level to represent energy drain for large ships
 */
public class EntityCapacitorLevelModifierLarge
    extends RealAttribute
{
    public static final EntityCapacitorLevelModifierLarge INSTANCE = new EntityCapacitorLevelModifierLarge();

    @Override
    public int getId() {
        return  1897;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
