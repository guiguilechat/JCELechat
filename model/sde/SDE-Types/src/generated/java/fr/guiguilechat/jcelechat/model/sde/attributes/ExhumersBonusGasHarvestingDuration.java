package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ExhumersBonusGasHarvestingDuration
    extends IntAttribute
{
    public static final ExhumersBonusGasHarvestingDuration INSTANCE = new ExhumersBonusGasHarvestingDuration();

    @Override
    public int getId() {
        return  3226;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ExhumersBonusGasHarvestingDuration";
    }
}
