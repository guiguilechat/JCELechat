package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class PauseArmorRepairDpsThreshold
    extends IntAttribute
{
    public static final PauseArmorRepairDpsThreshold INSTANCE = new PauseArmorRepairDpsThreshold();

    @Override
    public int getId() {
        return  3355;
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
        return "PauseArmorRepairDpsThreshold";
    }
}
