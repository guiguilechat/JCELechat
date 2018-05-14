package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Sets the radius of the asteroid ball when it has a quantity of 1 unit
 */
public class AsteroidRadiusUnitSize
    extends IntAttribute
{
    public final static AsteroidRadiusUnitSize INSTANCE = new AsteroidRadiusUnitSize();

    @Override
    public int getId() {
        return  1981;
    }

    @Override
    public int getCatId() {
        return  31;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  90.0;
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
