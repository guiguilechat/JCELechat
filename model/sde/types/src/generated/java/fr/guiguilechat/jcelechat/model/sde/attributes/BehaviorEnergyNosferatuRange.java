package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorEnergyNosferatuRange
    extends IntAttribute
{
    public static final BehaviorEnergyNosferatuRange INSTANCE = new BehaviorEnergyNosferatuRange();

    @Override
    public int getId() {
        return  2632;
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
        return "BehaviorEnergyNosferatuRange";
    }
}
