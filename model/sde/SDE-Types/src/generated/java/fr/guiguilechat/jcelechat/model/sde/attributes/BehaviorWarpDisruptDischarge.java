package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class BehaviorWarpDisruptDischarge
    extends DoubleAttribute
{
    public static final BehaviorWarpDisruptDischarge INSTANCE = new BehaviorWarpDisruptDischarge();

    @Override
    public int getId() {
        return  2505;
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
        return "BehaviorWarpDisruptDischarge";
    }
}
