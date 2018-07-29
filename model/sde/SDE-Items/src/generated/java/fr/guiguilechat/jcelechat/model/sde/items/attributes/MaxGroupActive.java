package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
 */
public class MaxGroupActive
    extends IntAttribute
{
    public final static MaxGroupActive INSTANCE = new MaxGroupActive();

    @Override
    public int getId() {
        return  763;
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

    @Override
    public String toString() {
        return "MaxGroupActive";
    }
}
