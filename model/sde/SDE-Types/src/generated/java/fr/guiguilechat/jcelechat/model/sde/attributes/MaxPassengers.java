package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Specifies the maximum numbers of passengers that the ship can have
 */
public class MaxPassengers
    extends IntAttribute
{
    public static final MaxPassengers INSTANCE = new MaxPassengers();

    @Override
    public int getId() {
        return  129;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MaxPassengers";
    }
}
