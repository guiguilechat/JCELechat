package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The base cost of hiring an ally into a war
 */
public class BaseDefenderAllyCost
    extends IntAttribute
{
    public final static BaseDefenderAllyCost INSTANCE = new BaseDefenderAllyCost();

    @Override
    public int getId() {
        return  1820;
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
}
