package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
 */
public class EntityReactionFactor
    extends DoubleAttribute
{
    public static final EntityReactionFactor INSTANCE = new EntityReactionFactor();

    @Override
    public int getId() {
        return  466;
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
        return "EntityReactionFactor";
    }
}
