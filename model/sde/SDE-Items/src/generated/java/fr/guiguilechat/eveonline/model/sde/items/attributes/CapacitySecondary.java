package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Secondary cargo space allowed, meant to supplement capacity. This is currently used exclusively for Strontium storage on starbases.
 */
public class CapacitySecondary
    extends IntAttribute
{
    public final static CapacitySecondary INSTANCE = new CapacitySecondary();

    @Override
    public int getId() {
        return  1233;
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
        return  10000.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
