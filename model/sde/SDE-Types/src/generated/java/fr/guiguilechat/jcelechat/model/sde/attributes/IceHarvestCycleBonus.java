package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class IceHarvestCycleBonus
    extends DoubleAttribute
{
    public static final IceHarvestCycleBonus INSTANCE = new IceHarvestCycleBonus();

    @Override
    public int getId() {
        return  780;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "IceHarvestCycleBonus";
    }
}