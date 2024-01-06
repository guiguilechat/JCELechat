package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemEnergyNeutFittingReduction
    extends IntAttribute
{
    public static final SubsystemEnergyNeutFittingReduction INSTANCE = new SubsystemEnergyNeutFittingReduction();

    @Override
    public int getId() {
        return  2665;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SubsystemEnergyNeutFittingReduction";
    }
}
