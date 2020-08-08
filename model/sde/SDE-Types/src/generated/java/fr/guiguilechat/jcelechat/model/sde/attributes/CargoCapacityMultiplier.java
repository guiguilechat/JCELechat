package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to the amount of cargo capacity for a ship.
 */
public class CargoCapacityMultiplier
    extends DoubleAttribute
{
    public static final CargoCapacityMultiplier INSTANCE = new CargoCapacityMultiplier();

    @Override
    public int getId() {
        return  149;
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
