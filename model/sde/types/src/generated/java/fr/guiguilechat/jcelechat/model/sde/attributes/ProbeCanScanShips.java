package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If this is 1 then the probe can scan for ships, otherwise it can't.
 */
public class ProbeCanScanShips
    extends IntAttribute
{
    public static final ProbeCanScanShips INSTANCE = new ProbeCanScanShips();

    @Override
    public int getId() {
        return  1413;
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
        return "ProbeCanScanShips";
    }
}
