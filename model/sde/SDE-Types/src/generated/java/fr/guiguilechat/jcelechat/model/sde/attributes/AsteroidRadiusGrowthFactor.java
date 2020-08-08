package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Controls how quickly an asteroid radius increases as its quantity grows.
 */
public class AsteroidRadiusGrowthFactor
    extends DoubleAttribute
{
    public static final AsteroidRadiusGrowthFactor INSTANCE = new AsteroidRadiusGrowthFactor();

    @Override
    public int getId() {
        return  1980;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
