package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * If this is 1 then the probe can scan for ships, otherwise it can't.
 */
public class ProbeCanScanShips
    extends IntAttribute
{
    public final static ProbeCanScanShips INSTANCE = new ProbeCanScanShips();

    @Override
    public int getId() {
        return  1413;
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
