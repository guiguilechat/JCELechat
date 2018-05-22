package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class SubsystemEnergyNeutFittingReduction
    extends IntAttribute
{
    public final static SubsystemEnergyNeutFittingReduction INSTANCE = new SubsystemEnergyNeutFittingReduction();

    @Override
    public int getId() {
        return  2665;
    }

    @Override
    public int getCatId() {
        return  1;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
