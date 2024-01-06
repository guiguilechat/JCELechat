package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * the amount of armor that is repaired per cycle to each target
 */
public class NpcRemoteArmorRepairAmount
    extends IntAttribute
{
    public static final NpcRemoteArmorRepairAmount INSTANCE = new NpcRemoteArmorRepairAmount();

    @Override
    public int getId() {
        return  1455;
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
        return "NpcRemoteArmorRepairAmount";
    }
}
