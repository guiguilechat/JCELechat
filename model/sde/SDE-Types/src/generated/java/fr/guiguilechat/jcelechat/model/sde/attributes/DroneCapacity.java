package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This defines the total capacity of drones allowed in the drone bay of the ship
 */
public class DroneCapacity
    extends IntAttribute
{
    public static final DroneCapacity INSTANCE = new DroneCapacity();

    @Override
    public int getId() {
        return  283;
    }

    @Override
    public int getCatId() {
        return  10;
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
        return "DroneCapacity";
    }
}
