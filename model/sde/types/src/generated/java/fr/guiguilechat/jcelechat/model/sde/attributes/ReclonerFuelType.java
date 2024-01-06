package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Type of fuel consumed by tactical capsuleer recloner
 */
public class ReclonerFuelType
    extends IntAttribute
{
    public static final ReclonerFuelType INSTANCE = new ReclonerFuelType();

    @Override
    public int getId() {
        return  3105;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "ReclonerFuelType";
    }
}
