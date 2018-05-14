package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
 */
public class EntityReactionFactor
    extends DoubleAttribute
{
    public final static EntityReactionFactor INSTANCE = new EntityReactionFactor();

    @Override
    public int getId() {
        return  466;
    }

    @Override
    public int getCatId() {
        return  6;
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
}
