package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class MiningFrigateBonusIceHarvestingDuration
    extends IntAttribute
{
    public static final MiningFrigateBonusIceHarvestingDuration INSTANCE = new MiningFrigateBonusIceHarvestingDuration();

    @Override
    public int getId() {
        return  3240;
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
        return "MiningFrigateBonusIceHarvestingDuration";
    }
}
