package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The distance from a target an entity starts using its weapons.
 */
public class EntityAttackRange
    extends IntAttribute
{
    public static final EntityAttackRange INSTANCE = new EntityAttackRange();

    @Override
    public int getId() {
        return  247;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  15000.0;
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
        return "EntityAttackRange";
    }
}
