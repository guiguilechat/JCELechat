package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BehaviorSensorDampenerDischarge
    extends RealAttribute
{
    public static final BehaviorSensorDampenerDischarge INSTANCE = new BehaviorSensorDampenerDischarge();

    @Override
    public int getId() {
        return  2530;
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
        return "BehaviorSensorDampenerDischarge";
    }
}
