package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Deprecated.
 */
public class MinTargetVelDmgMultiplier
    extends RealAttribute
{
    public static final MinTargetVelDmgMultiplier INSTANCE = new MinTargetVelDmgMultiplier();

    @Override
    public int getId() {
        return  662;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "MinTargetVelDmgMultiplier";
    }
}
