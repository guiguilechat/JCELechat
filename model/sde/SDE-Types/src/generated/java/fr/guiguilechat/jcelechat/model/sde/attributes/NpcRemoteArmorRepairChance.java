package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * the chance of the NPC remote reapiring it's comrads.
 */
public class NpcRemoteArmorRepairChance
    extends DoubleAttribute
{
    public static final NpcRemoteArmorRepairChance INSTANCE = new NpcRemoteArmorRepairChance();

    @Override
    public int getId() {
        return  1453;
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
        return "NpcRemoteArmorRepairChance";
    }
}
