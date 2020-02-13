package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Max Range for NPC Target Jam
 */
public class ECMRangeOptimal
    extends IntAttribute
{
    public static final ECMRangeOptimal INSTANCE = new ECMRangeOptimal();

    @Override
    public int getId() {
        return  936;
    }

    @Override
    public int getCatId() {
        return  25;
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
        return "ECMRangeOptimal";
    }
}
