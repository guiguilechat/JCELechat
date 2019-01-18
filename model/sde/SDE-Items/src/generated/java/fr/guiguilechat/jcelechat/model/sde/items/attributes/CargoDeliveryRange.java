package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Distance which players can deposit cargo into a structure
 */
public class CargoDeliveryRange
    extends IntAttribute
{
    public static final CargoDeliveryRange INSTANCE = new CargoDeliveryRange();

    @Override
    public int getId() {
        return  2790;
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
        return "CargoDeliveryRange";
    }
}
