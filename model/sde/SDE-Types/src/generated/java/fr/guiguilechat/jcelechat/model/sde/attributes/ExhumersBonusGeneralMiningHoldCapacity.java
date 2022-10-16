package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class ExhumersBonusGeneralMiningHoldCapacity
    extends RealAttribute
{
    public static final ExhumersBonusGeneralMiningHoldCapacity INSTANCE = new ExhumersBonusGeneralMiningHoldCapacity();

    @Override
    public int getId() {
        return  3198;
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
        return "ExhumersBonusGeneralMiningHoldCapacity";
    }
}
