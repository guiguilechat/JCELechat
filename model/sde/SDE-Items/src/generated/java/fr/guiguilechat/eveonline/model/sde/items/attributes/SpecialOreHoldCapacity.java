package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Capacity of ore-only hold
 */
public class SpecialOreHoldCapacity
    extends IntAttribute
{
    public final static SpecialOreHoldCapacity INSTANCE = new SpecialOreHoldCapacity();

    @Override
    public int getId() {
        return  1556;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return true;
    }

    @Override
    public String toString() {
        return "SpecialOreHoldCapacity";
    }
}
