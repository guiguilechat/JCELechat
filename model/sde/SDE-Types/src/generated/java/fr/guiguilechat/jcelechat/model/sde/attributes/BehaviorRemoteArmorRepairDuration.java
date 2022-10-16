package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BehaviorRemoteArmorRepairDuration
    extends RealAttribute
{
    public static final BehaviorRemoteArmorRepairDuration INSTANCE = new BehaviorRemoteArmorRepairDuration();

    @Override
    public int getId() {
        return  2491;
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
        return "BehaviorRemoteArmorRepairDuration";
    }
}
