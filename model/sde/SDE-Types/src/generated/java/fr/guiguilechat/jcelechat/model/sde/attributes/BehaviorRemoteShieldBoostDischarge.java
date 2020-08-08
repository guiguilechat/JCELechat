package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class BehaviorRemoteShieldBoostDischarge
    extends DoubleAttribute
{
    public static final BehaviorRemoteShieldBoostDischarge INSTANCE = new BehaviorRemoteShieldBoostDischarge();

    @Override
    public int getId() {
        return  2498;
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
        return "BehaviorRemoteShieldBoostDischarge";
    }
}
