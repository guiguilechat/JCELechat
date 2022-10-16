package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * special subsystem hold capacity
 */
public class SpecialSubsystemHoldCapacity
    extends IntAttribute
{
    public static final SpecialSubsystemHoldCapacity INSTANCE = new SpecialSubsystemHoldCapacity();

    @Override
    public int getId() {
        return  2675;
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
        return false;
    }

    @Override
    public String toString() {
        return "SpecialSubsystemHoldCapacity";
    }
}
