package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of shield boost effect
 */
public class NpcRemoteShieldBoostDuration
    extends IntAttribute
{
    public static final NpcRemoteShieldBoostDuration INSTANCE = new NpcRemoteShieldBoostDuration();

    @Override
    public int getId() {
        return  1458;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  20000.0;
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
        return "NpcRemoteShieldBoostDuration";
    }
}
