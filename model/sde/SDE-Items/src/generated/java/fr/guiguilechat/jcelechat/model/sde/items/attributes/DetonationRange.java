package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * the range in meters for an object to trigger detonation of missile. (own ship excluded)
 */
public class DetonationRange
    extends IntAttribute
{
    public final static DetonationRange INSTANCE = new DetonationRange();

    @Override
    public int getId() {
        return  108;
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
        return "DetonationRange";
    }
}
