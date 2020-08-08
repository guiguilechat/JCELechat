package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
 */
public class DisallowTethering
    extends IntAttribute
{
    public static final DisallowTethering INSTANCE = new DisallowTethering();

    @Override
    public int getId() {
        return  2343;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DisallowTethering";
    }
}
