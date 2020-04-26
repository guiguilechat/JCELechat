package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum number of targets that can be repaired at once.
 */
public class NpcRemoteArmorRepairMaxTargets
    extends IntAttribute
{
    public static final NpcRemoteArmorRepairMaxTargets INSTANCE = new NpcRemoteArmorRepairMaxTargets();

    @Override
    public int getId() {
        return  1501;
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

    @Override
    public String toString() {
        return "NpcRemoteArmorRepairMaxTargets";
    }
}
