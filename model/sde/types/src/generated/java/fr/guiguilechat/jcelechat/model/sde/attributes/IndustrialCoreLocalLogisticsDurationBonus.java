package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IndustrialCoreLocalLogisticsDurationBonus
    extends IntAttribute
{
    public static final IndustrialCoreLocalLogisticsDurationBonus INSTANCE = new IndustrialCoreLocalLogisticsDurationBonus();

    @Override
    public int getId() {
        return  2606;
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
        return "IndustrialCoreLocalLogisticsDurationBonus";
    }
}
