package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class MiningBargeBonusGasHarvestingDuration
    extends IntAttribute
{
    public static final MiningBargeBonusGasHarvestingDuration INSTANCE = new MiningBargeBonusGasHarvestingDuration();

    @Override
    public int getId() {
        return  3183;
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
        return "MiningBargeBonusGasHarvestingDuration";
    }
}
