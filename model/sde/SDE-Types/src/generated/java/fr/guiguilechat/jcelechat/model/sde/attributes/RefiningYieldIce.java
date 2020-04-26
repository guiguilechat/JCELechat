package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class RefiningYieldIce
    extends DoubleAttribute
{
    public static final RefiningYieldIce INSTANCE = new RefiningYieldIce();

    @Override
    public int getId() {
        return  2448;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.5;
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
        return "RefiningYieldIce";
    }
}
