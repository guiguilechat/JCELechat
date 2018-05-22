package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
 */
public class DisallowTethering
    extends IntAttribute
{
    public final static DisallowTethering INSTANCE = new DisallowTethering();

    @Override
    public int getId() {
        return  2343;
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
