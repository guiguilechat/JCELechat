package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * How many structures in this group can be anchored for the same alliance per solar system.  0 means there is no limit.
 */
public class PosAnchoredPerSolarSystemAmount
    extends IntAttribute
{
    public final static PosAnchoredPerSolarSystemAmount INSTANCE = new PosAnchoredPerSolarSystemAmount();

    @Override
    public int getId() {
        return  1195;
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
}
