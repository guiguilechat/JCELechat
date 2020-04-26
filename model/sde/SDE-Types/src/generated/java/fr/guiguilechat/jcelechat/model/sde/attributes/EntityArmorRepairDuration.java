package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration between armor repair actions for entities.
 */
public class EntityArmorRepairDuration
    extends IntAttribute
{
    public static final EntityArmorRepairDuration INSTANCE = new EntityArmorRepairDuration();

    @Override
    public int getId() {
        return  630;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EntityArmorRepairDuration";
    }
}
