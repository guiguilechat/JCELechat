package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * How damaged does a teammate's armor need to be before it will be repaired.
 *  0.1 means: Must be below 90% armor to get repairs
 *  0.9 means: Must be below 10% armor to get repairs
 */
public class NpcRemoteArmorRepairThreshold
    extends DoubleAttribute
{
    public static final NpcRemoteArmorRepairThreshold INSTANCE = new NpcRemoteArmorRepairThreshold();

    @Override
    public int getId() {
        return  1456;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.25;
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
        return "NpcRemoteArmorRepairThreshold";
    }
}
