package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The base cost of hiring an ally into a war
 */
public class BaseDefenderAllyCost
    extends IntAttribute
{
    public static final BaseDefenderAllyCost INSTANCE = new BaseDefenderAllyCost();

    @Override
    public int getId() {
        return  1820;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0E7;
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
        return "BaseDefenderAllyCost";
    }
}
