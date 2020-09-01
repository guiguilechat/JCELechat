package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class IceHarvestCycleBonus
    extends RealAttribute
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
        return "IceHarvestCycleBonus";
    }
}
