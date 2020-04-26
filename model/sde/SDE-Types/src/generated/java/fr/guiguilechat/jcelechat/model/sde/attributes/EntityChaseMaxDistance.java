package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The distance outside of which the entity activates their MWD equivalent.
 */
public class EntityChaseMaxDistance
    extends IntAttribute
{
    public static final EntityChaseMaxDistance INSTANCE = new EntityChaseMaxDistance();

    @Override
    public int getId() {
        return  665;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  2500.0;
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
        return "EntityChaseMaxDistance";
    }
}
