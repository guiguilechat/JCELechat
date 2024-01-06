package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * modifier to an entity capacitor level to represent energy drain for small ships
 */
public class EntityCapacitorLevelModifierSmall
    extends RealAttribute
{
    public static final EntityCapacitorLevelModifierSmall INSTANCE = new EntityCapacitorLevelModifierSmall();

    @Override
    public int getId() {
        return  1895;
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
        return "EntityCapacitorLevelModifierSmall";
    }
}
