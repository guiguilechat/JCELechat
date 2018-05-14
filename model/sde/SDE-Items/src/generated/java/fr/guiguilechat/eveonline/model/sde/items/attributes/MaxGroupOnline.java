package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Maximum modules of same group that can be onlined at same time, 0 = no limit, 1 = 1
 */
public class MaxGroupOnline
    extends IntAttribute
{
    public final static MaxGroupOnline INSTANCE = new MaxGroupOnline();

    @Override
    public int getId() {
        return  978;
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
