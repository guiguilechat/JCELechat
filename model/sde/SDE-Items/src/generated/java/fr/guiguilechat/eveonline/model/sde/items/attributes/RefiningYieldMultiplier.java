package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The factor by which the structure modifies the using pilot's refining yield rate.
 */
public class RefiningYieldMultiplier
    extends DoubleAttribute
{
    public final static RefiningYieldMultiplier INSTANCE = new RefiningYieldMultiplier();

    @Override
    public int getId() {
        return  717;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.5;
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
        return "RefiningYieldMultiplier";
    }
}
