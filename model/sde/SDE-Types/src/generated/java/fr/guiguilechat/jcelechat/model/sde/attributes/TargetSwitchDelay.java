package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of time after attacking a target that an entity will wait before switching to a new one.
 */
public class TargetSwitchDelay
    extends IntAttribute
{
    public static final TargetSwitchDelay INSTANCE = new TargetSwitchDelay();

    @Override
    public int getId() {
        return  691;
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

    @Override
    public String toString() {
        return "TargetSwitchDelay";
    }
}
