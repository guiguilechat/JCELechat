package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Amount of armor repaired per cycle for entities.
 */
public class EntityArmorRepairAmount
    extends RealAttribute
{
    public static final EntityArmorRepairAmount INSTANCE = new EntityArmorRepairAmount();

    @Override
    public int getId() {
        return  631;
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
        return "EntityArmorRepairAmount";
    }
}
