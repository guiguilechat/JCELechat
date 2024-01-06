package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How many structures in this group can be anchored for the same alliance per solar system.  0 means there is no limit.
 */
public class PosAnchoredPerSolarSystemAmount
    extends IntAttribute
{
    public static final PosAnchoredPerSolarSystemAmount INSTANCE = new PosAnchoredPerSolarSystemAmount();

    @Override
    public int getId() {
        return  1195;
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
        return "PosAnchoredPerSolarSystemAmount";
    }
}
