package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplier to the amount of cargo capacity for a ship.
 */
public class CargoCapacityMultiplier
    extends DoubleAttribute
{
    public final static CargoCapacityMultiplier INSTANCE = new CargoCapacityMultiplier();

    @Override
    public int getId() {
        return  149;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return "CargoCapacityMultiplier";
    }
}
