package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BehaviorEnergyNosferatuDischarge
    extends RealAttribute
{
    public static final BehaviorEnergyNosferatuDischarge INSTANCE = new BehaviorEnergyNosferatuDischarge();

    @Override
    public int getId() {
        return  2629;
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
        return "BehaviorEnergyNosferatuDischarge";
    }
}
