package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class CpuPenaltyPercent
    extends DoubleAttribute
{
    public static final CpuPenaltyPercent INSTANCE = new CpuPenaltyPercent();

    @Override
    public int getId() {
        return  1082;
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
        return "CpuPenaltyPercent";
    }
}
