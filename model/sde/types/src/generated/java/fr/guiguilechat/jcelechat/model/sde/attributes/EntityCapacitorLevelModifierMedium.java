package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * modifier to an entity capacitor level to represent energy drain for medium ships
 */
public class EntityCapacitorLevelModifierMedium
    extends RealAttribute
{
    public static final EntityCapacitorLevelModifierMedium INSTANCE = new EntityCapacitorLevelModifierMedium();

    @Override
    public int getId() {
        return  1896;
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
        return "EntityCapacitorLevelModifierMedium";
    }
}
