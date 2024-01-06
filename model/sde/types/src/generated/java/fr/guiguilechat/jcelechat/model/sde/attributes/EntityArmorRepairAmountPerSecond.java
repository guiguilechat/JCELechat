package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * the average armor amount repaired per second
 */
public class EntityArmorRepairAmountPerSecond
    extends RealAttribute
{
    public static final EntityArmorRepairAmountPerSecond INSTANCE = new EntityArmorRepairAmountPerSecond();

    @Override
    public int getId() {
        return  1892;
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
        return "EntityArmorRepairAmountPerSecond";
    }
}
