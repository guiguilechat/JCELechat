package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Deprecated.
 */
public class MinTargetVelDmgMultiplier
    extends DoubleAttribute
{
    public final static MinTargetVelDmgMultiplier INSTANCE = new MinTargetVelDmgMultiplier();

    @Override
    public int getId() {
        return  662;
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
