package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This type can only be found/used/created on a planet matching this type ID.
 */
public class PlanetRestriction
    extends IntAttribute
{
    public static final PlanetRestriction INSTANCE = new PlanetRestriction();

    @Override
    public int getId() {
        return  1632;
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
        return "PlanetRestriction";
    }
}
