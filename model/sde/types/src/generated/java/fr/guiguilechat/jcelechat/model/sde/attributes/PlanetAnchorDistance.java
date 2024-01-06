package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
