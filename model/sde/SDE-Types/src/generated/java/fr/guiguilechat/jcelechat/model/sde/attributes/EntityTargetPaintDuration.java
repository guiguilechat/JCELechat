package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of NPC effect
 */
public class EntityTargetPaintDuration
    extends IntAttribute
{
    public static final EntityTargetPaintDuration INSTANCE = new EntityTargetPaintDuration();

    @Override
    public int getId() {
        return  945;
    }

    @Override
    public int getCatId() {
        return  21;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  30000.0;
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
        return "EntityTargetPaintDuration";
    }
}
