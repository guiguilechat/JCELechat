package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum number of targets that can be shield boosted at once
 */
public class NpcRemoteShieldBoostMaxTargets
    extends IntAttribute
{
    public static final NpcRemoteShieldBoostMaxTargets INSTANCE = new NpcRemoteShieldBoostMaxTargets();

    @Override
    public int getId() {
        return  1502;
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
        return "NpcRemoteShieldBoostMaxTargets";
    }
}
