package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class BehaviorCommandBurstRange
    extends IntAttribute
{
    public static final BehaviorCommandBurstRange INSTANCE = new BehaviorCommandBurstRange();

    @Override
    public int getId() {
        return  5971;
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
        return false;
    }

    @Override
    public String toString() {
        return "BehaviorCommandBurstRange";
    }
}
