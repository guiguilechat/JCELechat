package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of fuel consumed by tactical capsuleer recloner
 */
public class ReclonerFuelQuantity
    extends IntAttribute
{
    public static final ReclonerFuelQuantity INSTANCE = new ReclonerFuelQuantity();

    @Override
    public int getId() {
        return  3104;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ReclonerFuelQuantity";
    }
}
