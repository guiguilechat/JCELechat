package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorRemoteShieldBoostDuration
    extends IntAttribute
{
    public static final BehaviorRemoteShieldBoostDuration INSTANCE = new BehaviorRemoteShieldBoostDuration();

    @Override
    public int getId() {
        return  2495;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "BehaviorRemoteShieldBoostDuration";
    }
}
