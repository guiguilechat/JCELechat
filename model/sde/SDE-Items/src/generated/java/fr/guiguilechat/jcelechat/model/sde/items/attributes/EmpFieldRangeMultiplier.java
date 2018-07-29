package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Smart bomb range multiplier
 */
public class EmpFieldRangeMultiplier
    extends DoubleAttribute
{
    public final static EmpFieldRangeMultiplier INSTANCE = new EmpFieldRangeMultiplier();

    @Override
    public int getId() {
        return  1487;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "EmpFieldRangeMultiplier";
    }
}
