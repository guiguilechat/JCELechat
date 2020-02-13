package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Manufacturing cost multiplyer
 */
public class ManufactureCostMultiplier
    extends IntAttribute
{
    public static final ManufactureCostMultiplier INSTANCE = new ManufactureCostMultiplier();

    @Override
    public int getId() {
        return  369;
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
        return  1.0;
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
        return "ManufactureCostMultiplier";
    }
}
