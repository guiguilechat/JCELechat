package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Sets the radius of the asteroid ball when it has a quantity of 1 unit
 */
public class AsteroidRadiusUnitSize
    extends IntAttribute
{
    public static final AsteroidRadiusUnitSize INSTANCE = new AsteroidRadiusUnitSize();

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

    @Override
    public String toString() {
        return "AsteroidRadiusUnitSize";
    }
}
