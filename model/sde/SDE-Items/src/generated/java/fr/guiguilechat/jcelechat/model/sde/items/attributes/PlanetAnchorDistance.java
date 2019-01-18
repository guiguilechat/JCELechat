package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * How many meters from the standard warp-in distance a planet can be anchored from.
 */
public class PlanetAnchorDistance
    extends IntAttribute
{
    public static final PlanetAnchorDistance INSTANCE = new PlanetAnchorDistance();

    @Override
    public int getId() {
        return  865;
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
        return  100000.0;
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
        return "PlanetAnchorDistance";
    }
}
