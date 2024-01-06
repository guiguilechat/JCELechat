package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How long NPC take to remote repair ther comerad in MS.
 */
public class NpcRemoteArmorRepairDuration
    extends IntAttribute
{
    public static final NpcRemoteArmorRepairDuration INSTANCE = new NpcRemoteArmorRepairDuration();

    @Override
    public int getId() {
        return  1454;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  10000.0;
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
        return "NpcRemoteArmorRepairDuration";
    }
}
