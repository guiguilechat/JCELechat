package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * special subsystem hold capacity
 */
public class SpecialSubsystemHoldCapacity
    extends IntAttribute
{
    public final static SpecialSubsystemHoldCapacity INSTANCE = new SpecialSubsystemHoldCapacity();

    @Override
    public int getId() {
        return  2675;
    }

    @Override
    public int getCatId() {
        return  40;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
}
