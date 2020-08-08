package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum attack delay time for entity.
 */
public class EntityAttackDelayMax
    extends IntAttribute
{
    public static final EntityAttackDelayMax INSTANCE = new EntityAttackDelayMax();

    @Override
    public int getId() {
        return  476;
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
        return "EntityAttackDelayMax";
    }
}
