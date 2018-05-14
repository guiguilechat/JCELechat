package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The amount of time after attacking a target that an entity will wait before switching to a new one.
 */
public class TargetSwitchDelay
    extends IntAttribute
{
    public final static TargetSwitchDelay INSTANCE = new TargetSwitchDelay();

    @Override
    public int getId() {
        return  691;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
