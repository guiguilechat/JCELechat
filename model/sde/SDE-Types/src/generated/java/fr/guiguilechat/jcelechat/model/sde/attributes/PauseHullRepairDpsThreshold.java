package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class PauseHullRepairDpsThreshold
    extends IntAttribute
{
    public static final PauseHullRepairDpsThreshold INSTANCE = new PauseHullRepairDpsThreshold();

    @Override
    public int getId() {
        return  3356;
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
        return "PauseHullRepairDpsThreshold";
    }
}
