package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class MiningBargeBonusIceHarvestingRange
    extends IntAttribute
{
    public static final MiningBargeBonusIceHarvestingRange INSTANCE = new MiningBargeBonusIceHarvestingRange();

    @Override
    public int getId() {
        return  3185;
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
        return "MiningBargeBonusIceHarvestingRange";
    }
}
