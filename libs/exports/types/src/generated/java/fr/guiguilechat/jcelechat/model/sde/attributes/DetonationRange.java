package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * the range in meters for an object to trigger detonation of missile. (own ship excluded)
 */
public class DetonationRange
    extends IntAttribute
{
    public static final DetonationRange INSTANCE = new DetonationRange();

    @Override
    public int getId() {
        return  108;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
