package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorRemoteArmorRepairFalloff
    extends IntAttribute
{
    public static final BehaviorRemoteArmorRepairFalloff INSTANCE = new BehaviorRemoteArmorRepairFalloff();

    @Override
    public int getId() {
        return  2493;
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
        return "BehaviorRemoteArmorRepairFalloff";
    }
}
