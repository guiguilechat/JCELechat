package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Controls how quickly an asteroid radius increases as its quantity grows.
 */
public class AsteroidRadiusGrowthFactor
    extends DoubleAttribute
{
    public final static AsteroidRadiusGrowthFactor INSTANCE = new AsteroidRadiusGrowthFactor();

    @Override
    public int getId() {
        return  1980;
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
        return  1.0;
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
        return "AsteroidRadiusGrowthFactor";
    }
}
