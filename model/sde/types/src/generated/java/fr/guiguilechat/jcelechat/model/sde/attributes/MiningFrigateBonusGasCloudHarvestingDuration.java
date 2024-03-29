package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class MiningFrigateBonusGasCloudHarvestingDuration
    extends IntAttribute
{
    public static final MiningFrigateBonusGasCloudHarvestingDuration INSTANCE = new MiningFrigateBonusGasCloudHarvestingDuration();

    @Override
    public int getId() {
        return  3237;
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
        return "MiningFrigateBonusGasCloudHarvestingDuration";
    }
}
