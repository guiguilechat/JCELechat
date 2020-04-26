package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How many shields points does the activation of the effect bestow upon the target
 */
public class NpcRemoteShieldBoostAmount
    extends IntAttribute
{
    public static final NpcRemoteShieldBoostAmount INSTANCE = new NpcRemoteShieldBoostAmount();

    @Override
    public int getId() {
        return  1460;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  50.0;
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
        return "NpcRemoteShieldBoostAmount";
    }
}
