package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Deprecated.
 */
public class MinTargetVelDmgMultiplier
    extends DoubleAttribute
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
    public double getDefaultValue() {
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