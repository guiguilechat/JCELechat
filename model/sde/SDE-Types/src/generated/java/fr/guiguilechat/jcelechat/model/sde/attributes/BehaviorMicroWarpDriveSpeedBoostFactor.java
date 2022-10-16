package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorMicroWarpDriveSpeedBoostFactor
    extends IntAttribute
{
    public static final BehaviorMicroWarpDriveSpeedBoostFactor INSTANCE = new BehaviorMicroWarpDriveSpeedBoostFactor();

    @Override
    public int getId() {
        return  2619;
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
        return "BehaviorMicroWarpDriveSpeedBoostFactor";
    }
}
